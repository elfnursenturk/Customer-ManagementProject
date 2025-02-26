package com.elifnursenturk.controller;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.elifnursenturk.dto.DtoUser;


import jakarta.validation.Valid;

public interface IUserController {
	
	ResponseEntity<?> createCustomer(@RequestBody @Valid DtoUser dtoUser, BindingResult bindingResult);
	
	public DtoUser findCustomerById(Long id);
	
	public Page<DtoUser> findAllCustomers(Pageable pageable);
	
	public DtoUser uptadeCustomer(Long id, DtoUser updatedCustomer);
	
	 ResponseEntity<DtoUser> deleteCustomer(Long id);
	 
	
	



	
}
