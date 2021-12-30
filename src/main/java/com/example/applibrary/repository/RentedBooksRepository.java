package com.example.applibrary.repository;

import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.helper.GeneralData;
import com.example.applibrary.model.EncapsulatedRentedBooks;
import com.example.applibrary.model.EncapsulatedUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentedBooksRepository {


	static Connection conn;

	public RentedBooksRepository() {
		conn = DBConnection.getConnection();
	}


	public  List<EncapsulatedRentedBooks> findAll() {

		List<EncapsulatedRentedBooks> listOfBook = new ArrayList<EncapsulatedRentedBooks>();
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM rentedbooks");
			while (rs.next()) {
				listOfBook.add(new EncapsulatedRentedBooks(
						Integer.parseInt(rs.getString("ID")),
						rs.getString("UserLogin"),
						rs.getString("BookName"),
						Integer.parseInt(rs.getString("RentCost")),
						rs.getDate("StartDate"),
						rs.getDate("ExpirationDate")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

	public static void deleteBook(EncapsulatedRentedBooks book) {

		String sql = "delete from rentedbooks where id = '" + book.getId() + "'";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<EncapsulatedRentedBooks> getRentedBooksByUser(EncapsulatedUsers user) {

		List<EncapsulatedRentedBooks> listOfBook = new ArrayList<EncapsulatedRentedBooks>();
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM rentedbooks WHERE UserLogin LIKE '" + GeneralData.getLoggedUser().getLogin() + "' ");
			while (rs.next()) {
				listOfBook.add(
						new EncapsulatedRentedBooks(Integer.parseInt(
								rs.getString("id")),
								rs.getString("BookName"),
								Integer.parseInt(rs.getString("rentCost")),
								rs.getDate("StartDate"),
								rs.getDate("ExpirationDate")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

}
