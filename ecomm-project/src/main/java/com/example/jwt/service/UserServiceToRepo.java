package com.example.jwt.service;

import com.example.jwt.exception.ExceptionBroker;
import com.example.jwt.model.Users;
import com.example.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceToRepo {

	private UserRepository userRepository;

	@Autowired
	public UserServiceToRepo(UserRepository userRepositoryImpl) {
		this.userRepository = userRepositoryImpl;
	}

	public void insertUserService(Users user, String userName) {

		Boolean userExists = userRepository.existsById(user.getSub());
		if (!userExists) {
			user.setName(userName);
			userRepository.save(user);
		}
	}

	public Users getUserByID(String userID) throws ExceptionBroker {
		Optional<Users> opUser = userRepository.findById(userID);

		if (!opUser.isPresent()) {
			throw new ExceptionBroker("user not found", HttpStatus.NOT_FOUND);
		}
		return opUser.get();
	}

	@Transactional
	public Users updateUser(Users user) {
		Optional<Users> opUser = userRepository.findById(user.getSub());
		Users userToSave = opUser.get();
		userRepository.save(userToSave);
		return userToSave;
	}

}
