/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.service;

import com.co.brayanfsilvar.ctrlactividades.dao.ActividadDao;
import com.co.brayanfsilvar.ctrlactividades.domain.Actividad;
import com.co.brayanfsilvar.ctrlactividades.general.Utilidad;
import java.util.List;

/**
 *
 * @author BrayanFSilvaR
 */
public class ActividadService {
 
    private ActividadDao actividadDao = null;
    private Utilidad util = null;

    public ActividadService() {
        actividadDao = new ActividadDao();
        util = new Utilidad();
    }

    private Actividad saveActividad(Actividad newActividad) {
        return actividadDao.saveActividad(newActividad);
    }

    private Actividad updateActividad(Actividad updateActividad) {
        return actividadDao.updateActividad(updateActividad);
    }

    private void deleteActividad(Actividad deleteActividad) {
        actividadDao.deleteActividad(deleteActividad);
    }

    private Actividad findActividadById(Long id) {
        return actividadDao.findActividadById(id);
    }

    private List<Actividad> findAllActividades() {
        return actividadDao.findAllActividades();
    }

    
}
