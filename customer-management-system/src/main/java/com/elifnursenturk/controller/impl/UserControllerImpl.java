package com.elifnursenturk.controller.impl;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.elifnursenturk.controller.IUserController;
import com.elifnursenturk.dto.DtoUser;
import com.elifnursenturk.error.ApiError;
import com.elifnursenturk.service.IUserService;
import com.elifnursenturk.shared.GenericMessage;

import jakarta.validation.Valid;
import lombok.experimental.var;
@RestController
@RequestMapping("/rest/api/user")
public  class UserControllerImpl implements IUserController {

	@Autowired
	private IUserService userService;
	@PostMapping(path = "/save")
	@Override
	public ResponseEntity<?> createCustomer(@RequestBody @Valid DtoUser dtoUser, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        ApiError apiError = new ApiError();
	        apiError.setPath("/rest/api/user/save");
	        apiError.setMessage("Validation Error");
	        apiError.setStatus(400);

	        Map<String, String> validationErrors = new HashMap<>();
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            validationErrors.put(error.getField(), error.getDefaultMessage());
	        }
	        apiError.setValidationErrors(validationErrors);

	        return ResponseEntity.badRequest().body(apiError);
	    }

	    DtoUser savedUser = userService.createCustomer(dtoUser);
	    GenericMessage<DtoUser> message = new GenericMessage<>("User is created", savedUser);

	    return ResponseEntity.ok(message);
	}


	@GetMapping(path = "/list")
	@Override

	public Page<DtoUser> findAllCustomers(Pageable pageable) {
	    return userService.findAllCustomers(pageable);
	}
	
	@GetMapping(path = "/list/{id}")
	@Override
	public DtoUser findCustomerById(@PathVariable(name = "id") Long id) {
		return userService.findCustomerById(id);
	}

	@Override
	@PutMapping(path = "/update/{id}")
	public DtoUser uptadeCustomer(@PathVariable(name = "id") Long id, @RequestBody DtoUser updatedCustomer) {
		return userService.uptadeCustomer(id, updatedCustomer);
	}

	@Override
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<DtoUser> deleteCustomer(@PathVariable Long id) {
        ResponseEntity<DtoUser> response = userService.deleteCustomer(id);
        if (response.getBody() == null) {
            return ResponseEntity.status(404).body(null);
        }
        return response; 
    }




/*
@ExceptionHandler(MethodArgumentNotValidException.class)
ApiError handleMethodArgNotValidEx(MethodArgumentNotValidException exception) {
    ApiError apiError = new ApiError();
    apiError.setPath("/rest/api/user/save");
    apiError.setMessage("Validation Error");
    apiError.setStatus(400);
    Map<String, String> validationErrors = new HashMap<>();

    for (var fieldError : exception.getBindingResult().getFieldErrors()) {
		validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
	}
    apiError.setValidationErrors(validationErrors);
	return apiError;
}*/


}
