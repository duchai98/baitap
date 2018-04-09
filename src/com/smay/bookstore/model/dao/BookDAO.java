package com.smay.bookstore.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.smay.bookstore.model.bean.Book;

public class BookDAO {

    private ConnectionSQL connection;    
     
    public BookDAO(String hostName, String sqlInstanceName, String port, String database, String userName, String password) {
		connection = new ConnectionSQL(hostName, sqlInstanceName, port, database, userName, password);
	}
     
    public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        connection.connect();
         
        PreparedStatement statement = connection.connection().prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        connection.disconnect();
        return rowInserted;
    }
     
    public ArrayList<Book> listAllBooks() throws SQLException {
        ArrayList<Book> listBook = new ArrayList<>();
         
        String sql = "SELECT * FROM book";
         
        connection.connect();
         
        Statement statement = connection.connection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
             
            Book book = new Book(id, title, author, price);
            listBook.add(book);
        }
         
        resultSet.close();
        statement.close();
         
        connection.disconnect();
         
        return listBook;
    }
     
    public boolean deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM book where book_id = ?";
         
        connection.connect();
         
        PreparedStatement statement = connection.connection().prepareStatement(sql);
        statement.setInt(1, book.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        connection.disconnect();
        return rowDeleted;     
    }
     
    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, price = ?";
        sql += " WHERE book_id = ?";
        connection.connect();
         
        PreparedStatement statement = connection.connection().prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        statement.setInt(4, book.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        connection.disconnect();
        return rowUpdated;     
    }
     
    public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";
         
        connection.connect();
         
        PreparedStatement statement = connection.connection().prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
             
            book = new Book(id, title, author, price);
        }
         
        resultSet.close();
        statement.close();
        connection.disconnect();
         
        return book;
    }
}
