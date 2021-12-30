package com.example.applibrary.service;

import java.util.List;

import com.example.applibrary.model.EncapsulatedBooks;
import com.example.applibrary.repository.LibraryRepository;

public class LibraryService {

	private LibraryRepository libraryRepository;
	
	
	//constructor default
	public LibraryService() {
		libraryRepository = new LibraryRepository();
	}

	
	//find all favorited books for a certain user
	public List<EncapsulatedBooks> findAll() {
		return libraryRepository.findAll();
	}

	
}
