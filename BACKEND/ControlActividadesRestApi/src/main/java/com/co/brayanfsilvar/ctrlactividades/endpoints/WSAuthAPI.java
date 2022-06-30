/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.endpoints;

import com.co.brayanfsilvar.ctrlactividades.general.ApplicationLog;
import com.co.brayanfsilvar.ctrlactividades.general.Utilidad;
import com.co.brayanfsilvar.ctrlactividades.model.request.RequestAuthAPI;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponseAuthAPI;
import com.co.brayanfsilvar.ctrlactividades.service.AuthAPIServiceBean;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author BrayanFSilvaR
 */
@Path("AuthAPI")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class WSAuthAPI extends ApplicationLog {

    private Utilidad util = null;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSAuthAPI
     */
    public WSAuthAPI() {
        //CONSTRUCTOR
        this.util = new Utilidad();
    }

    @POST
    public ResponseAuthAPI authenticateRest(RequestAuthAPI requestAuth) {
        ResponseAuthAPI responseAuth = new ResponseAuthAPI();
        try {
            responseAuth = new AuthAPIServiceBean().AuthenticateAPI(requestAuth);
        } catch (Exception e) {
            logFatal(" authenticateRest || " + this.util.obtenerMsjExcepcion(e));
            responseAuth.setbRta(Boolean.FALSE);
            responseAuth.setsMsg("Se ha presentado un inconveniente al generar el token.");
        }
        return responseAuth;
    }
}
