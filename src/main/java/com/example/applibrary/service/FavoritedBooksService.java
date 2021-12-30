package com.example.applibrary.service;

import java.util.List;

import com.example.applibrary.helper.GeneralData;
import com.example.applibrary.model.EncapsulatedBooks;
import com.example.applibrary.model.EncapsulatedFavoriteBooks;
import com.example.applibrary.model.EncapsulatedUsers;
import com.example.applibrary.repository.FavoritedBooksRepository;
import com.example.applibrary.repository.LibraryRepository;

public class FavoritedBooksService {

	private LibraryRepository libraryRepository;
	private FavoritedBooksRepository FavoritedBooksRepository ;
	private EncapsulatedUsers loggedUser;
	
	//constructor default
	public FavoritedBooksService() {
		
		//initializing repository (already get the connection to database) and get logged user
		libraryRepository = new LibraryRepository();
		FavoritedBooksRepository = new FavoritedBooksRepository();
		loggedUser = GeneralData.getLoggedUser();
	}

	//add favorited book for certain user
	public void add(EncapsulatedBooks book) {
		FavoritedBooksRepository.addFavoritedBookByUser(book, loggedUser);
	}
	
	
	//delete favorited book with selected id
	public void delete(EncapsulatedFavoriteBooks book) {
		FavoritedBooksRepository.deleteBook(book);
	}
	
	
	//find all favorited books for a certain user
	public List<EncapsulatedFavoriteBooks> findByUser() {
		return FavoritedBooksRepository.getFavoritedBooksByUser(loggedUser);
	}
	
	
	//find all books dont favorited by user
	public List<EncapsulatedBooks> findBooksDontFavoritedByUser() {
		return libraryRepository.findBooksDontFavoritedByUser(loggedUser);
	}
	
	
	
	//getters and setters for private variables 
	public FavoritedBooksRepository getFavoritedBooksRepository() {
		return FavoritedBooksRepository;
	}

	public void setFavoritedBooksRepository(FavoritedBooksRepository FavoritedBooksRepository) {
		this.FavoritedBooksRepository = FavoritedBooksRepository;
	}

	public EncapsulatedUsers getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(EncapsulatedUsers loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	
	
	
	
}
