/*package com.elifnursenturk.controller.impl;

import com.elifnursenturk.jwt.AuthRequest;
import com.elifnursenturk.controller.IRestAuthController;
import com.elifnursenturk.dto.DtoUser;
import com.elifnursenturk.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

   
    @Autowired
    private IAuthService authService;

    @Override
    public ResponseEntity<DtoUser> login(AuthRequest authRequest) {
        try {
           
            return authService.login(authRequest);  
        } catch (Exception e) {
           
            return ResponseEntity.badRequest().body(null); 
        }
    }
}*/
