/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.dao;

import com.co.brayanfsilvar.ctrlactividades.domain.Actividad;
import com.co.brayanfsilvar.ctrlactividades.general.GenericRepositoryJPA;
import java.util.List;

/**
 *
 * @author BrayanFSilvaR
 */
public class ActividadDao extends GenericRepositoryJPA<Actividad> {

    public Actividad saveActividad(Actividad newActividad) {
        return create(newActividad);
    }

    public Actividad updateActividad(Actividad updateActividad) {
        return update(updateActividad);
    }

    public void deleteActividad(Actividad deleteActividad) {
        delete(deleteActividad);
    }

    public Actividad findActividadById(Long id) {
        return find(id);
    }

    public List<Actividad> findAllActividades() {
        return findAll();
    }
}
