/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.endpoints;

import com.co.brayanfsilvar.ctrlactividades.domain.Persona;

import com.co.brayanfsilvar.ctrlactividades.model.response.ResponsePersonas;
import com.co.brayanfsilvar.ctrlactividades.security.Secured;
import com.co.brayanfsilvar.ctrlactividades.service.PersonaService;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author BrayanFSilvaR
 */
@Path("Persona")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class WSPersona {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSPersona
     */
    public WSPersona() {
        //Constructor
    }

    @GET
    @Path("/listarEmpleados")
    @Secured
    public ResponsePersonas obtenerEmpleados() {
        return new PersonaService().listadoEmpleados();
    }
}
