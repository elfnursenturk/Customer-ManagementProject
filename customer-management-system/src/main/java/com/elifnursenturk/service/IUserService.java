package com.elifnursenturk.service;


import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.elifnursenturk.dto.DtoUser;
import com.elifnursenturk.shared.GenericMessage;

public interface IUserService {

	
	public DtoUser createCustomer(DtoUser dtoUser);
	
	public DtoUser findCustomerById(Long id);
	
	public Page<DtoUser> findAllCustomers(Pageable pageable);
	
	public DtoUser uptadeCustomer(Long id, DtoUser updatedCustomer);
	
	ResponseEntity<DtoUser> deleteCustomer(Long id);
	
}
