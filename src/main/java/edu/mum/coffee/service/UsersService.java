package edu.mum.coffee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Users;
import edu.mum.coffee.repository.PersonRepository;
import edu.mum.coffee.repository.UsersRepository;

@Service
@Transactional
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	public Users saveUser(Users user) {
		return usersRepository.save(user);
	}

	public List<Users> findByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
}
