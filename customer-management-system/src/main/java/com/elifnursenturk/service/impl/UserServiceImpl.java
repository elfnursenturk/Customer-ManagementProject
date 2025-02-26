package com.elifnursenturk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elifnursenturk.dto.DtoCustomer;
import com.elifnursenturk.dto.DtoUser;
import com.elifnursenturk.model.Customer;
import com.elifnursenturk.model.User;
import com.elifnursenturk.repository.CustomerRepository;
import com.elifnursenturk.repository.UserRepository;
import com.elifnursenturk.service.IUserService;



@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  CustomerRepository customerRepository;
	
	BCryptPasswordEncoder  passwordEncoder = new BCryptPasswordEncoder();
	
	
	@Override
	public DtoUser createCustomer(DtoUser dtoUser) {
    User user = new User();
    user.setUsername(dtoUser.getUsername());
    user.setPassword((dtoUser.getPassword()));


    User savedUser = userRepository.save(user); 

  
    Customer customer = new Customer();
    customer.setFirstName(dtoUser.getCustomer().getFirstName());
    customer.setLastName(dtoUser.getCustomer().getLastName());
    customer.setTckn(dtoUser.getCustomer().getTckn());

    customer.setUser(savedUser); 
    Customer savedCustomer = customerRepository.save(customer); 

  
    savedUser.setCustomer(savedCustomer);
    userRepository.save(savedUser); 


    DtoUser responseDtoUser = new DtoUser();
    BeanUtils.copyProperties(savedUser, responseDtoUser);

    DtoCustomer dtoCustomer = new DtoCustomer();
    BeanUtils.copyProperties(savedCustomer, dtoCustomer);

    responseDtoUser.setCustomer(dtoCustomer);

    return responseDtoUser;
    
	}

	@Override
	public DtoUser findCustomerById(Long id) {
	 Optional<User> optional=userRepository.findById(id);
	 DtoUser dtoUser = new DtoUser();
	 DtoCustomer dtoCustomer = new DtoCustomer();
		 if(optional.isEmpty()) {
			 return null;
		 }
	 User user = optional.get();
	 Customer customer = user.getCustomer();
	 BeanUtils.copyProperties(user, dtoUser);
	 BeanUtils.copyProperties(customer, dtoCustomer);
	 dtoUser.setCustomer(dtoCustomer);
	 
		return dtoUser;
	}
	@Override
	public Page<DtoUser> findAllCustomers(Pageable pageable) {
	    Page<User> userList = userRepository.findAll(pageable);
	    
	    List<DtoUser> dtoList = new ArrayList<>();
	    
	    if (!userList.isEmpty()) {
	        for (User user : userList) {
	            DtoUser dtoUser = new DtoUser();
	            BeanUtils.copyProperties(user, dtoUser);
	            
	  
	            Customer customer = user.getCustomer();
	            if (customer != null) {
	                DtoCustomer dtoCustomer = new DtoCustomer(
	                        customer.getId(),
	                        customer.getFirstName(),
	                        customer.getLastName(),
	                        customer.getTckn(),
	                        customer.getRegistrationDate()
	                );
	                dtoUser.setCustomer(dtoCustomer);
	            }

	            dtoList.add(dtoUser);
	        }
	    }
	   
	    return new PageImpl<>(dtoList, pageable, userList.getTotalElements());
	}
	
	
	@Override
	public DtoUser uptadeCustomer(Long id, DtoUser updatedCustomer) {
		
	Optional<User> optional =userRepository.findById(id);
		if(optional.isPresent()) {
			
		User user=optional.get();
		Customer customer = user.getCustomer();
		
		customer.setFirstName(updatedCustomer.getCustomer().getFirstName());
		customer.setLastName(updatedCustomer.getCustomer().getLastName());
		customer.setTckn(updatedCustomer.getCustomer().getTckn());
		customer.setRegistrationDate(updatedCustomer.getCustomer().getRegistrationDate());
		user.setUsername(updatedCustomer.getUsername());
		user.setPassword(updatedCustomer.getPassword());
		
		User NewUser=userRepository.save(user);
		BeanUtils.copyProperties(NewUser,updatedCustomer);
		return updatedCustomer;
		}

	return null;
	
	}


	@Override
	public ResponseEntity<DtoUser> deleteCustomer(Long id) {
	    Optional<User> optional = userRepository.findById(id);
	    if (optional.isPresent()) {
	        User user = optional.get();
	        
	       
	        DtoUser dtoUser = new DtoUser();
	        dtoUser.setUserid(user.getUserid()); 
	        dtoUser.setUsername(user.getUsername());
	        dtoUser.setPassword(user.getPassword());
	        
	       
	        if (user.getCustomer() != null) {
	            DtoCustomer dtoCustomer = new DtoCustomer();
	            dtoCustomer.setId(user.getCustomer().getId());
	            dtoCustomer.setFirstName(user.getCustomer().getFirstName());
	            dtoCustomer.setLastName(user.getCustomer().getLastName());
	            dtoCustomer.setTckn(user.getCustomer().getTckn());
	            dtoCustomer.setRegistrationDate(user.getCustomer().getRegistrationDate());

	            dtoUser.setCustomer(dtoCustomer);
	        } else {
	            dtoUser.setCustomer(null);  
	        }

	       
	        userRepository.delete(user);
	        
	        return ResponseEntity.ok(dtoUser); 
	    }

	    return ResponseEntity.status(404).body(null);  
	}


	
	
	
	
}
