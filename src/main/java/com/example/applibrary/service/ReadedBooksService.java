package com.example.applibrary.service;

import com.example.applibrary.helper.GeneralData;
import com.example.applibrary.model.EncapsulatedBooks;
import com.example.applibrary.model.EncapsulatedReadedBooks;
import com.example.applibrary.model.EncapsulatedUsers;
import com.example.applibrary.repository.LibraryRepository;
import com.example.applibrary.repository.ReadedBooksRepository;

import java.util.List;

public class ReadedBooksService {

	private LibraryRepository libraryRepository;
	private ReadedBooksRepository readedBooksRepository ;
	private EncapsulatedUsers loggedUser;
	
	//constructor default
	public ReadedBooksService() {
		
		//initializing repository (already get the connection to database) and get logged user
		libraryRepository = new LibraryRepository();
		readedBooksRepository = new ReadedBooksRepository();
		loggedUser = GeneralData.getLoggedUser();

	}

	//add readed book for certain user
	public void add(EncapsulatedBooks book) {
		readedBooksRepository.addReadedBookByUser(book, loggedUser);
	}
	
	
	//delete readed book with selected id
	public void delete(EncapsulatedReadedBooks book) {
		readedBooksRepository.deleteBook(book);
	}
	
	
	//find all readed books for a certain user
	public List<EncapsulatedReadedBooks> findByUser() {
		return readedBooksRepository.getReadedBooksByUser(loggedUser);
	}
	
	
	//find all books dont readed by user
	public List<EncapsulatedBooks> findBooksDontReadedByUser() {
		return libraryRepository.findBooksDontReadedByUser(loggedUser);
	}
	
	
	
	//getters and setters for private variables 
	public ReadedBooksRepository getReadedBooksRepository() {
		return readedBooksRepository;
	}

	public void setReadedBooksRepository(ReadedBooksRepository readedBooksRepository) {
		this.readedBooksRepository = readedBooksRepository;
	}

	public EncapsulatedUsers getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(EncapsulatedUsers loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	
	
	
	
}
