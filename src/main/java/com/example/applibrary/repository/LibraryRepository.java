package com.example.applibrary.repository;

import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.model.EncapsulatedBooks;
import com.example.applibrary.model.EncapsulatedRentedBooks;
import com.example.applibrary.model.EncapsulatedUsers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryRepository {

	static Connection conn;

	public LibraryRepository() {
		conn = DBConnection.getConnection();
	}
	
	public List<EncapsulatedBooks> findAll() {
		List<EncapsulatedBooks> listOfBook = new ArrayList<EncapsulatedBooks>();
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM library");
			while (rs.next()) {
				listOfBook.add(new EncapsulatedBooks(Integer.parseInt(rs.getString("ID")), rs.getString("BookName"),
						rs.getString("Author"), Integer.parseInt(rs.getString("PublishingDate")),
						Integer.parseInt(rs.getString("SellCost")), Integer.parseInt(rs.getString("RentCost")),
						Integer.parseInt(rs.getString("Amount"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

	public List<EncapsulatedRentedBooks> findAllRentedBooks() {
		List<EncapsulatedRentedBooks> listOfBook = new ArrayList<EncapsulatedRentedBooks>();
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM rentedbooks");
			while (rs.next()) {
				listOfBook.add(new EncapsulatedRentedBooks(Integer.parseInt(rs.getString("id")), rs.getString("login"),
						rs.getString("name"), Integer.parseInt(rs.getString("rentCost")),
						rs.getDate("startDate"),rs.getDate("expirationDate")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

	public  List<EncapsulatedBooks> findBooksDontReadedByUser(EncapsulatedUsers user) {
		List<EncapsulatedBooks> listOfBook = new ArrayList<EncapsulatedBooks>();
		try {
			String sql = "select\r\n" + "	*\r\n" + "from\r\n" + "	library l\r\n" + "where\r\n"
					+ "	l.BookName not in (\r\n" + "	select\r\n" + "		r.BookName \r\n" + "	from\r\n"
					+ "		readbooks r\r\n" + "	where\r\n" + "		r.UserLogin = " + "'" + user.getLogin() + "')";

			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				listOfBook.add(new EncapsulatedBooks(Integer.parseInt(rs.getString("ID")), rs.getString("BookName"),
						rs.getString("Author"), Integer.parseInt(rs.getString("PublishingDate")),
						Integer.parseInt(rs.getString("SellCost")), Integer.parseInt(rs.getString("RentCost")),
						Integer.parseInt(rs.getString("Amount"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

	
	//books dont favorited by user
	public  List<EncapsulatedBooks> findBooksDontFavoritedByUser(EncapsulatedUsers user) {
		List<EncapsulatedBooks> listOfBook = new ArrayList<EncapsulatedBooks>();
		try {
			String sql = "select\r\n" + "	*\r\n" + "from\r\n" + "	library l\r\n" + "where\r\n"
					+ "	l.BookName not in (\r\n" + "	select\r\n" + "		r.BookName \r\n" + "	from\r\n"
					+ "		favoritebooks r\r\n" + "	where\r\n" + "		r.UserLogin = " + "'" + user.getLogin() + "')";

			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				listOfBook.add(new EncapsulatedBooks(Integer.parseInt(rs.getString("ID")), rs.getString("BookName"),
						rs.getString("Author"), Integer.parseInt(rs.getString("PublishingDate")),
						Integer.parseInt(rs.getString("SellCost")), Integer.parseInt(rs.getString("RentCost")),
						Integer.parseInt(rs.getString("Amount"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

    public List<EncapsulatedBooks> findBooksDontRentedByUser(EncapsulatedUsers user) {

		List<EncapsulatedBooks> listOfBook = new ArrayList<EncapsulatedBooks>();
		try {
			String sql = "select\r\n" + "	*\r\n" + "from\r\n" + "	library l\r\n" + "where\r\n"
					+ "	l.BookName not in (\r\n" + "	select\r\n" + "		r.BookName \r\n" + "	from\r\n"
					+ "		rentedbooks r\r\n" + "	where\r\n" + "		r.UserLogin = " + "'" + user.getLogin() + "')";

			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				listOfBook.add(new EncapsulatedBooks(Integer.parseInt(rs.getString("ID")), rs.getString("BookName"),
						rs.getString("Author"), Integer.parseInt(rs.getString("PublishingDate")),
						Integer.parseInt(rs.getString("SellCost")), Integer.parseInt(rs.getString("RentCost")),
						Integer.parseInt(rs.getString("Amount"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
    }

}
