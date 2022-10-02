package oop.io.demo.auth.security.jwt;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.*;
import oop.io.demo.auth.security.services.UserDetailImplementation;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    //@Value("${ams.app.jwtSecret}")
    private static String jwtSecret= "yl123";

    //@Value("${ams.app.jwtExpirationMs}")
    private static int jwtExpirationMs = 86400000;


    public String generateJwtToken(Authentication authentication) {
        UserDetailImplementation userPrincipal = (UserDetailImplementation) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        return true;
        } catch (SignatureException e) {
        logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
        logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
        logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
        logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
        logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
    
    public static String generateTokenFromEmail(String email) {   
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
}