/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.service;

import com.co.brayanfsilvar.ctrlactividades.model.request.RequestAuthAPI;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponseAuthAPI;
import com.co.brayanfsilvar.ctrlactividades.security.JwtTokenHelper;
import com.co.brayanfsilvar.ctrlactividades.general.ApplicationLog;
import com.co.brayanfsilvar.ctrlactividades.general.Utilidad;

/**
 *
 * @author BrayanFSilvaR
 */
public class AuthAPIServiceBean extends ApplicationLog {

    private JwtTokenHelper jwtTokenHelper;
    private String stComplementoLog = " AuthenticateAPI || ";
    private Utilidad util = null;

    public AuthAPIServiceBean() {
        this.jwtTokenHelper = new JwtTokenHelper();
        this.util = new Utilidad();
    }

    public ResponseAuthAPI AuthenticateAPI(RequestAuthAPI requestAuth) {
        ResponseAuthAPI responseAuth = new ResponseAuthAPI();
        try {
            logInfo(stComplementoLog + requestAuth.toString());
            String user = "CTRL_ACTIVIDADES_2022";
            String pass = "ee134d30-f173-4adb-bd6c-a7ecffd3fcc8";
            String sUserDecode = "";
            String sPassDecode = "";
            if (requestAuth.getsUser() != null && !requestAuth.getsUser().equals("") && requestAuth.getsPass() != null && !requestAuth.getsPass().equals("")) {
                sUserDecode = requestAuth.getsUser();
                sPassDecode = util.decodeBase64(requestAuth.getsPass());
                if (sUserDecode.equals(user) && sPassDecode.equals(pass)) {
                    String token = jwtTokenHelper.issueTokenAuth();
                    responseAuth.setbRta(Boolean.TRUE);
                    responseAuth.setsToken(token);
                    responseAuth.setsMsg("Se ha generado el token correctamente");
                } else {
                    responseAuth.setbRta(Boolean.FALSE);
                    responseAuth.setsMsg("Datos incorrectos!");
                }
            } else {
                responseAuth.setbRta(Boolean.FALSE);
                responseAuth.setsMsg("Sin datos para validar.");
            }

        } catch (Exception e) {
            logFatal(stComplementoLog + this.util.obtenerMsjExcepcion(e));
            responseAuth.setbRta(Boolean.FALSE);
            responseAuth.setsMsg("Se ha presentado un inconveniente tecnico al generar el token. ");
        } finally {
            logInfo(stComplementoLog + responseAuth.toString());
        }
        return responseAuth;
    }

}
