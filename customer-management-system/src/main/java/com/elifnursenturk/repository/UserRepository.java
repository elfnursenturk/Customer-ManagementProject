package com.elifnursenturk.repository;


import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elifnursenturk.dto.DtoUser;
import com.elifnursenturk.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	 Page<User> findAll(Pageable pageable); 
	 @Query(value = "from User where username = :username")
	 Optional<User> findByUsername(String username);
}
