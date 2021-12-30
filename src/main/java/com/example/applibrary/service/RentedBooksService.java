package com.example.applibrary.service;

import com.example.applibrary.helper.GeneralData;
import com.example.applibrary.model.EncapsulatedRentedBooks;
import com.example.applibrary.model.EncapsulatedUsers;
import com.example.applibrary.repository.LibraryRepository;
import com.example.applibrary.repository.RentedBooksRepository;

import java.util.List;

public class RentedBooksService {

	private RentedBooksRepository rentedBooksRepository;
	private LibraryRepository libraryRepository;
	private EncapsulatedUsers loggedUser;

	public RentedBooksService() {
		rentedBooksRepository = new RentedBooksRepository();
		libraryRepository = new LibraryRepository();
		loggedUser = GeneralData.getLoggedUser();
	}

	//constructor default

	public List<EncapsulatedRentedBooks> findAll() {
		return rentedBooksRepository.findAll();
	}

	public void delete(EncapsulatedRentedBooks book) {
		RentedBooksRepository.deleteBook(book);
	}

	//find all favorited books for a certain user
	public List<EncapsulatedRentedBooks> findByUser() {
		return RentedBooksRepository.getRentedBooksByUser(loggedUser);
	}
}
