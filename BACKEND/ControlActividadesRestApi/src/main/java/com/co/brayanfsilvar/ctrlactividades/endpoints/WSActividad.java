/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.endpoints;

import com.co.brayanfsilvar.ctrlactividades.domain.Actividad;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponseActividades;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponseGenerico;
import com.co.brayanfsilvar.ctrlactividades.service.ActividadService;
import com.co.brayanfsilvar.ctrlactividades.service.EstadoActividadService;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author BrayanFSilvaR
 */
@Path("Actividad")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class WSActividad {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSActividades
     */
    public WSActividad() {
        //Constructor
    }

//    @GET    
//    @Path("/listarActividades")
//    public List<Actividad> obtenerActividades() {
//        return new ActividadService().findAllActividades();
//    }

    @GET    
    @Path("/listarEstadoActividades")
    public ResponseActividades obtenerEstadoActividades()  {
        return new EstadoActividadService().obtenerListaEstadoActividades();
    }


    @POST
    @Path("/crearActividad")
    public ResponseGenerico crearActividad(Actividad actividad) {
        return new ResponseGenerico();
    }

    @POST
    @Path("/actualizarActividad")
    public ResponseGenerico actualizarActividad(Actividad actividad) {
        return new ResponseGenerico();
    }

    @POST
    @Path("/eliminarActividad")
    public ResponseGenerico eliminarActividad(Actividad actividad) {
        return new ResponseGenerico();
    }
}
