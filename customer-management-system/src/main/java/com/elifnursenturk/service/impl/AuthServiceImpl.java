/*package com.elifnursenturk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elifnursenturk.dto.DtoCustomer;
import com.elifnursenturk.dto.DtoUser;
import com.elifnursenturk.jwt.AuthRequest;
import com.elifnursenturk.jwt.jwtService;
import com.elifnursenturk.model.User;
import com.elifnursenturk.repository.UserRepository;
import com.elifnursenturk.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private jwtService jwtService;
    


    @Override
    public ResponseEntity<DtoUser> login(AuthRequest authRequest) {
        try {
            
            User user = userRepository.findByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

          
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            String token = jwtService.generateToken(user);

           
            DtoCustomer dtoCustomer = null;
            if (user.getCustomer() != null) {
                dtoCustomer = new DtoCustomer(
                    user.getCustomer().getId(),
                    user.getCustomer().getFirstName(),
                    user.getCustomer().getLastName(),
                    user.getCustomer().getTckn(),
                    user.getCustomer().getRegistrationDate()
                );
            }

          
            DtoUser dtoUser = new DtoUser(user.getUserid(), user.getUsername(), token, dtoCustomer);
            return ResponseEntity.ok(dtoUser); 
        } catch (Exception e) {
            
            return ResponseEntity.badRequest().body(null); 
        }
    }
}*/
