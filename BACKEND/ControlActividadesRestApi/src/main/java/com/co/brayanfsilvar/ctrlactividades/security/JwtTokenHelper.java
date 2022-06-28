/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Brayan F Silva R
 */
public class JwtTokenHelper {

    private final Long currentTimeInMillis = System.currentTimeMillis();

    public JwtTokenHelper() {
    }

    public String generateJwtTokenSucces(HashMap<String, Object> valToken) {
        return Jwts.builder()
                .setIssuer("APIActividad1")
                .setSubject("WS_CONSUMES_API")
                .setAudience("APP")
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDateSuccess())
                .setId(UUID.randomUUID().toString())
                .claim("valToken", valToken)
                .signWith(RestSecurityFilter.KEY, SignatureAlgorithm.HS512) //Algoritmo de encriptación
                .compact();
    }

    public String generateJwtTokenError(HashMap<String, Object> valToken) {
        return Jwts
                .builder()
                .setIssuer("APIActividad1")
                .setSubject("WS_CONSUMES_API")
                .setAudience("APP")
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate())
                .claim("valToken", valToken)
                .setId(UUID.randomUUID().toString())
                .signWith(RestSecurityFilter.KEY, SignatureAlgorithm.HS512) //Algoritmo de encriptación
                .compact();
    }

    public String issueTokenAuth() {
        //Se crea token                
        String jwtToken = Jwts.builder()
                .setIssuer("APIActividad1")
                .setSubject("WS_CONSUMES_API")
                .setAudience("APP")
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate())
                .setId(UUID.randomUUID().toString())
                .signWith(RestSecurityFilter.KEY, SignatureAlgorithm.HS512) //Algoritmo de encriptación
                .compact();
        return jwtToken;
    }

    public static Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(RestSecurityFilter.KEY.toString()))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    private Date getExpirationDate() {
        String sTimeOutSession = "5500";
        Long lTimeOutSession = new Long(sTimeOutSession);
        lTimeOutSession = lTimeOutSession * 1000;
        return new Date(currentTimeInMillis + lTimeOutSession);
    }

    private Date getExpirationDateSuccess() {
        String sTimeOutSession = "2250";
        Long lTimeOutSession = new Long(sTimeOutSession);
        lTimeOutSession = lTimeOutSession * 1000;
        return new Date(currentTimeInMillis + lTimeOutSession);
    }
}
