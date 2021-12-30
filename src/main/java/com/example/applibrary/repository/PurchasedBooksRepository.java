package com.example.applibrary.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.applibrary.helper.DBConnection;
import com.example.applibrary.model.EncapsulatedPurchasedBooks;
import com.example.applibrary.model.EncapsulatedUsers;

public class PurchasedBooksRepository {

	static Connection conn;

	public PurchasedBooksRepository() {
		conn = DBConnection.getConnection();
	}

	public  List<EncapsulatedPurchasedBooks> findAllPurchasedBooksByUser(EncapsulatedUsers user) {

		List<EncapsulatedPurchasedBooks> listOfBook = new ArrayList<EncapsulatedPurchasedBooks>();
		try {
			ResultSet rs = conn.createStatement()
					.executeQuery("SELECT id,BookName,SellCost,SellDay FROM soldbooks WHERE UserLogin = '"
							+ user.getLogin() + "'");
			while (rs.next()) {
				listOfBook.add(new EncapsulatedPurchasedBooks(Integer.parseInt(rs.getString("ID")),
						rs.getString("bookname"), Integer.parseInt(rs.getString("SellCost")), rs.getString("SellDay")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

}
