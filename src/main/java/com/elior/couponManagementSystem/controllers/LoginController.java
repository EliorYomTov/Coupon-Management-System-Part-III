package com.elior.couponManagementSystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elior.couponManagementSystem.login.ClientType;
import com.elior.couponManagementSystem.login.LoginCacheManger;
import com.elior.couponManagementSystem.login.LoginManager;
import com.elior.couponManagementSystem.security.Information;
import com.elior.couponManagementSystem.security.models.AuthenticationRequest;
import com.elior.couponManagementSystem.security.models.AuthenticationResponse;
import com.elior.couponManagementSystem.security.utils.ServicesUtils;
import com.elior.couponManagementSystem.security.utils.TokenManager;
import com.elior.couponManagementSystem.services.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginManager loginManager;
//	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final TokenManager jwtTokenUtil;

	private final LoginCacheManger loginCacheManger;

	@PostMapping("login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//					authenticationRequest.getEmail(), authenticationRequest.getPassword()));
//		} catch (BadCredentialsException e) {
//			throw new LoginFailedException("Incorrect email or password");
//		}
		ClientType clientType = authenticationRequest.getClientType();
		ClientService clientService = loginManager.login(authenticationRequest.getEmail(),
				authenticationRequest.getPassword(), clientType);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtTokenUtil.generateToken(userDetails, clientService, clientType);

		int clientServiceId = ServicesUtils.getClientServiceId(clientService, clientType);
		loginCacheManger.insertInfo(clientType.name() + clientServiceId, new Information(clientService, clientType));
		return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.CREATED);
	}

}
