package com.elifnursenturk.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DtoUser {

	   private Long userid;

	    @NotBlank(message = "Username cannot be blank")
	    private String username;

	    @NotBlank(message = "Password cannot be blank")
	    @Size(min = 6, message = "Password must be at least 6 characters")
	    private String password;

	    @Valid  
	    private DtoCustomer customer;
}
