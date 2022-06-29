/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.service;

import com.co.brayanfsilvar.ctrlactividades.dao.EstadoActividadDao;
import com.co.brayanfsilvar.ctrlactividades.domain.EstadoActividad;
import com.co.brayanfsilvar.ctrlactividades.general.ApplicationLog;
import com.co.brayanfsilvar.ctrlactividades.general.Utilidad;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponseActividades;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponseGenerico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BrayanFSilvaR
 */
public class EstadoActividadService extends ApplicationLog {

    private EstadoActividadDao actividadDao = null;
    private Utilidad util = null;

    public EstadoActividadService() {
        actividadDao = new EstadoActividadDao();
        util = new Utilidad();
    }

    private EstadoActividad saveEstadoActividad(EstadoActividad newEstadoActividad) {
        return actividadDao.saveEstadoActividad(newEstadoActividad);
    }

    private EstadoActividad updateEstadoActividad(EstadoActividad updateEstadoActividad) {
        return actividadDao.updateEstadoActividad(updateEstadoActividad);
    }

    private void deleteEstadoActividad(EstadoActividad deleteEstadoActividad) {
        actividadDao.deleteEstadoActividad(deleteEstadoActividad);
    }

    private EstadoActividad findEstadoActividadById(Long id) {
        return actividadDao.findEstadoActividadById(id);
    }

    private List<EstadoActividad> findAllEstadoActividades() {
        return actividadDao.findAllEstadoActividades();
    }

    public ResponseActividades obtenerListaEstadoActividades() {
        ResponseActividades responseActividades = new ResponseActividades();
        ResponseGenerico responseGenerico = new ResponseGenerico();
        try {
            List<EstadoActividad> listEstadoActividades = this.findAllEstadoActividades();
            if (listEstadoActividades != null && !listEstadoActividades.isEmpty()) {
                responseGenerico.setbSuccess(Boolean.TRUE);
                responseGenerico.setsMsj("Se han obtenido los estados de actividades");
                responseActividades.setResponseGenerico(responseGenerico);
                responseActividades.setListaEstadoActividades(listEstadoActividades);
            } else {
                responseGenerico.setbSuccess(Boolean.FALSE);
                responseGenerico.setsMsj("No se han encontrado los estados de actividades");
                responseActividades.setResponseGenerico(responseGenerico);
                responseActividades.setListaEstadoActividades(listEstadoActividades);
            }
        } catch (Exception e) {
            logFatal(" obtenerListaEstadoActividades || " + this.util.obtenerMsjExcepcion(e));
            responseGenerico.setbSuccess(Boolean.FALSE);
            responseGenerico.setsMsj("SE HA PRESENTANDO UN ERROR INESPERADO. INTENTE DE NUEVO MÁS TARDE.");
            responseActividades.setResponseGenerico(responseGenerico);
        }
        return responseActividades;
    }
}
