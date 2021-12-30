package com.example.applibrary.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.model.EncapsulatedBooks;
import com.example.applibrary.model.EncapsulatedReadedBooks;
import com.example.applibrary.model.EncapsulatedUsers;

//this class contains all database operations related to readedbooks

public class ReadedBooksRepository {

	static Connection conn;

	public ReadedBooksRepository() {
		conn = DBConnection.getConnection();
	}

	// get all readed books by certain user
	public  List<EncapsulatedReadedBooks> getReadedBooksByUser(EncapsulatedUsers user) {

		List<EncapsulatedReadedBooks> listOfBook = new ArrayList<EncapsulatedReadedBooks>();
		try {
			ResultSet rs = conn.createStatement()
					.executeQuery("SELECT * FROM readbooks WHERE UserLogin = '" + user.getLogin() + "'");
			while (rs.next()) {
				listOfBook.add(
						new EncapsulatedReadedBooks(Integer.parseInt(rs.getString("ID")), rs.getString("bookname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

	//
	public void addReadedBookByUser(EncapsulatedBooks book, EncapsulatedUsers user) {

		String sql = "INSERT INTO readbooks(UserLogin,BookName) VALUES ('" + user.getLogin() + "','" + book.getBook()
				+ "')";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(EncapsulatedReadedBooks book) {

		String sql = "delete from readbooks where id = '" + book.getId() + "'";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
