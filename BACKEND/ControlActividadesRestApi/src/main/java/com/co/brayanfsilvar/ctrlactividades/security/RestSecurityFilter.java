package com.co.brayanfsilvar.ctrlactividades.security;

import com.co.brayanfsilvar.ctrlactividades.general.ApplicationLog;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.security.Key;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 *
 * @author Brayan F Silva R
 */
@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class RestSecurityFilter extends ApplicationLog implements ContainerRequestFilter {

    public static final Key KEY = MacProvider.generateKey(SignatureAlgorithm.HS512);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Recupera la cabecera HTTP Authorization de la petición
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        logInfo(" filter || Se obtiene token del header de la petición || " + authorizationHeader);
        try {
            String token = "";
            // Extrae el token de la cabecera
            if (authorizationHeader != null && !authorizationHeader.equals("")) {
                logInfo(" filter || Se extrae token del header de la petición");
                token = authorizationHeader.substring("Bearer".length()).trim();
                // Valida el token utilizando la cadena secreta
                logInfo(" filter || Se valida token");
                if (token != null && !token.equals("")) {
                    Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
                } else {
                    logWarn(" filter || No hay token en la petición || " + Response.Status.UNAUTHORIZED);
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } else {
                logWarn(" filter || No hay token en la petición || " + Response.Status.FORBIDDEN);
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            String sMsjError = (e.getMessage() != null && !e.getMessage().isEmpty()) ? e.getMessage() : e.toString();
            logFatal(" filter || " + sMsjError);
            requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
        }
    }
}
