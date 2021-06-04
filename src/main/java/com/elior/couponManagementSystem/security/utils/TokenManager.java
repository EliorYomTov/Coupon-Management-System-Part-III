package com.elior.couponManagementSystem.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.elior.couponManagementSystem.login.ClientType;
import com.elior.couponManagementSystem.login.LoginCacheManger;
import com.elior.couponManagementSystem.services.ClientService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenManager {

//	private final Map<String, Information> map;
	private final LoginCacheManger loginCacheManger;

	private String SECRET_KEY = "secret";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public int extractClientId(String token) {
		final Claims claims = extractAllClaims(token);
		return (int) claims.get("id");
	}

	public String extractClientType(String token) {
		final Claims claims = extractAllClaims(token);
		return String.valueOf(claims.get("type"));
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails, ClientService clientService, ClientType clientType) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", ServicesUtils.getClientServiceId(clientService, clientType));
		claims.put("type", clientType.name());
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
//		Information information = new Information(clientService, clientType);
		String jwt = Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//		map.put(jwt, information);	
		return jwt;
	}

//	public ClientService getClientService(String token) throws SecuritySystemException{
//		if (map.get(token.substring(7)) == null)
//			throw new SecuritySystemException();
//		return map.get( token.substring(7)).getClientService();
//	}

	public Boolean validateToken(String token, UserDetails userDetails, ClientType clientTypeRequest) {
		ClientType clientTypeToken = loginCacheManger.getcClientType(extractClientType(token) + extractClientId(token));
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && clientTypeToken.equals(clientTypeRequest)
				&& !isTokenExpired(token));
	}
}
