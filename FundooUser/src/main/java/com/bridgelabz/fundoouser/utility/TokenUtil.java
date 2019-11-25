package com.bridgelabz.fundoouser.utility;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/******************************************************************************
 *  Compilation:  javac -d bin TokenUtil.java
 *  Execution:    
 *      
 *  Purpose:   Used to store utility methods
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
@Component
public class TokenUtil implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String SECRET_KEY ="secret"; 
	
	// Method which is used to create token
	public static String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
	}
	
	// Method which is used to generate token
	public static String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims,username);
	}
	
//	//Method used to validate token
//		public Boolean validateToken(String token1, String token2) {
//			return (token1.equals(token2));
//		}
		
//		@Value("${jwt.secret}")
//		private String secret;

		//retrieve username from jwt token
		public String getUsernameFromToken(String token) {
			Claims claim = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
			return claim.getSubject();
		}
		
	


}
