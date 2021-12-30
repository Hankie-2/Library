package com.example.applibrary.repository;

import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.model.EncapsulatedBooks;
import com.example.applibrary.model.EncapsulatedFavoriteBooks;
import com.example.applibrary.model.EncapsulatedUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritedBooksRepository {
	
	static Connection conn;

	public FavoritedBooksRepository() {
		conn = DBConnection.getConnection();
	}

	public  List<EncapsulatedFavoriteBooks> getFavoritedBooksByUser(EncapsulatedUsers user) {

		List<EncapsulatedFavoriteBooks> listOfBook = new ArrayList<EncapsulatedFavoriteBooks>();
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM favoritebooks WHERE UserLogin = '" + user.getLogin() + "'");
			while (rs.next()) {
				listOfBook.add(
						new EncapsulatedFavoriteBooks(Integer.parseInt(rs.getString("ID")), rs.getString("bookname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

	public void addFavoritedBookByUser(EncapsulatedBooks book, EncapsulatedUsers user) {

		String sql = "INSERT INTO favoritebooks(UserLogin,BookName) VALUES ('" + user.getLogin() + "','" + book.getBook() + "')";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(EncapsulatedFavoriteBooks book) {

		String sql = "delete from favoritebooks where id = '" + book.getId() + "'";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
