package com.example.applibrary.service;

import java.util.List;

import com.example.applibrary.helper.GeneralData;
import com.example.applibrary.model.EncapsulatedPurchasedBooks;
import com.example.applibrary.model.EncapsulatedUsers;
import com.example.applibrary.repository.PurchasedBooksRepository;

public class PurchasedBooksService {

	private PurchasedBooksRepository purchasedBooksRepository;
	private EncapsulatedUsers loggedUser;

	// constructor default
	public PurchasedBooksService() {

		purchasedBooksRepository = new PurchasedBooksRepository();
		loggedUser = GeneralData.getLoggedUser();
	}

	public List<EncapsulatedPurchasedBooks> findByUser() {
		return purchasedBooksRepository.findAllPurchasedBooksByUser(loggedUser);
	}

	public EncapsulatedUsers getLoggedUser() {
		return loggedUser;
	}

	public PurchasedBooksRepository getPurchasedBooksRepository() {
		return purchasedBooksRepository;
	}

	public void setPurchasedBooksRepository(PurchasedBooksRepository purchasedBooksRepository) {
		this.purchasedBooksRepository = purchasedBooksRepository;
	}

	public void setLoggedUser(EncapsulatedUsers loggedUser) {
		this.loggedUser = loggedUser;
	}

}
