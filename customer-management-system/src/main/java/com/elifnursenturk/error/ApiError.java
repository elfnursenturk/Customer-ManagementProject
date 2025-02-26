package com.elifnursenturk.error;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

	private int status;
	
	private String message;
	
	private String path;
	
	private long timeStamp = new Date().getTime();
	
	private Map<String, String> validationErrors = new HashMap<>();
	
	
}
