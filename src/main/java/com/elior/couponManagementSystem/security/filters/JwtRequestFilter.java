package com.elior.couponManagementSystem.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.elior.couponManagementSystem.login.ClientType;
import com.elior.couponManagementSystem.login.LoginCacheManger;
import com.elior.couponManagementSystem.security.services.MyUserDetailsService;
import com.elior.couponManagementSystem.security.utils.TokenManager;
import com.elior.couponManagementSystem.services.AdminService;
import com.elior.couponManagementSystem.services.ClientService;
import com.elior.couponManagementSystem.services.CompanyService;
import com.elior.couponManagementSystem.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

	private final MyUserDetailsService userDetailsService;
	private final TokenManager tokenManager;
	private final MyUserDetailsService MyUserDetailsService;
	private final LoginCacheManger loginCacheManger;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		MyUserDetailsService.initUsersDetails();
		final String authorizationHeader = request.getHeader("Authorization");
		ClientType clientTypeRequest = getclientTypeAccess(request.getRequestURI());
		String username = null;
		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = tokenManager.extractUsername(jwt);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (tokenManager.validateToken(jwt, userDetails, clientTypeRequest)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

				setClientServiceAsAttribute(request,
						loginCacheManger.getClientService(clientTypeRequest.name() + tokenManager.extractClientId(jwt)),
						clientTypeRequest);
			}
		}
		chain.doFilter(request, response);

		System.out.println("----------------------------------------------");
		System.out.println("Inside Servlet Filter");
		System.out.println("User IP address: " + request.getRemoteAddr());
		System.out.println("Local Port: " + request.getLocalPort());
		System.out.println("Server Name: " + request.getServerName());
		System.out.println("Protocol: " + request.getProtocol());
		System.out.println("Method: " + request.getMethod());
		System.out.println("Request URI: " + request.getRequestURI());
		System.out.println("Exiting Servlet Filter");
		System.out.println("----------------------------------------------");
	}

	private void setClientServiceAsAttribute(HttpServletRequest request, ClientService clientService,
			ClientType clientType) {
		switch (clientType) {
		case ADMINISTRATOR:
			request.setAttribute("service", (AdminService) clientService);
			break;
		case COMPANY:
			request.setAttribute("service", (CompanyService) clientService);
			break;
		case CUSTOMER:
			request.setAttribute("service", (CustomerService) clientService);
			break;
		}

	}

	private ClientType getclientTypeAccess(String requestURI) {
		String route = requestURI.split("/")[1];
		ClientType clientType = null;
		switch (route) {
		case "admin":
			clientType = ClientType.ADMINISTRATOR;
			break;
		case "company":
			clientType = ClientType.COMPANY;
			break;
		case "customer":
			clientType = ClientType.CUSTOMER;
			break;
		}
		return clientType;
	}

}