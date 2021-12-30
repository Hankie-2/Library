package com.example.applibrary.service;

import com.example.applibrary.model.EncapsulatedUsers;
import com.example.applibrary.repository.UserRepository;

import java.util.List;

public class UserService {

	private UserRepository userRepository;
	
	//constructor default
	public UserService() {
		userRepository = new UserRepository();
	}

	public List<EncapsulatedUsers> findAll() {
		return userRepository.findAll();
	}

	public List<EncapsulatedUsers> findAllNotLibrarian(){
		return userRepository.findAllNotLibrarian();
	}
	
}
