/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author BrayanFSilvaR
 */
public class JwtTokenHelper {

    private final Long currentTimeInMillis = System.currentTimeMillis();
    private static final String ST_ISSUER_JWT = "CTRL_ACTIVIDADES";
    private static final String ST_SUBJECT_JWT = "WS_CONSUMES_API";
    private static final String ST_ALG_JWT = "HS512";
    private static final String ST_TYP_JWT = "JWT";

    public JwtTokenHelper() {
        //Constructor
    }

    public String generateJwtTokenSucces(Map<String, Object> valToken) {

        return Jwts
                .builder()
                .setHeader(this.generarMapaCabecera())
                .setIssuer(ST_ISSUER_JWT)
                .setSubject(ST_SUBJECT_JWT)
                .setIssuedAt(new Date(currentTimeInMillis))
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS512, RestSecurityFilter.KEY)
                .claim("valToken", valToken)
                .compact();
    }

    public String generateJwtTokenError(Map<String, Object> valToken) {
        return Jwts
                .builder()
                .setHeader(this.generarMapaCabecera())
                .setIssuer(ST_ISSUER_JWT)
                .setSubject(ST_SUBJECT_JWT)
                .claim("valToken", valToken)
                .compact();
    }

    public String issueTokenAuth() {
        //SE OBTIENE FECHA Y HORA PARA CALCULAR VIGENCIA DE TOKEN
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(cal.getTime());
        cal1.add(Calendar.SECOND, 120);
        return Jwts.builder()
                .setHeader(this.generarMapaCabecera())
                .setIssuer(ST_ISSUER_JWT)
                .setSubject(ST_SUBJECT_JWT)
                .claim("bGenerateToken", Boolean.TRUE)
                .setIssuedAt(cal.getTime())
                .setExpiration(cal1.getTime())
                .signWith(SignatureAlgorithm.HS512, RestSecurityFilter.KEY) 
                .compact();
    }

    private Date getExpirationDate() {
        String sTimeOutSession = "980";
        Long lTimeOutSession = new Long(sTimeOutSession);
        lTimeOutSession = lTimeOutSession * 100000;
        return new Date(currentTimeInMillis + lTimeOutSession);
    }

    private Map<String, Object> generarMapaCabecera() {
        Map<String, Object> mapCabeceraToken = new HashMap<>();
        //SE ESTABLECE LA CABECERA
        mapCabeceraToken.put("typ", ST_TYP_JWT);
        mapCabeceraToken.put("alg", ST_ALG_JWT);
        return mapCabeceraToken;
    }
}
