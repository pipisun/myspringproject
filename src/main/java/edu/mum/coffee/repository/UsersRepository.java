package edu.mum.coffee.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	public List<Users> findByEmail(String email);
	
}
