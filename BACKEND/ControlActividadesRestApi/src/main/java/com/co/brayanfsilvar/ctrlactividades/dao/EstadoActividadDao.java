/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.dao;

import com.co.brayanfsilvar.ctrlactividades.domain.EstadoActividad;
import com.co.brayanfsilvar.ctrlactividades.general.GenericRepositoryJPA;
import java.util.List;

/**
 *
 * @author BrayanFSilvaR
 */
public class EstadoActividadDao extends GenericRepositoryJPA<EstadoActividad> {

    public EstadoActividad saveEstadoActividad(EstadoActividad newEstadoActividad) {
        return create(newEstadoActividad);
    }

    public EstadoActividad updateEstadoActividad(EstadoActividad updateEstadoActividad) {
        return update(updateEstadoActividad);
    }

    public void deleteEstadoActividad(EstadoActividad deleteEstadoActividad) {
        delete(deleteEstadoActividad);
    }

    public EstadoActividad findEstadoActividadById(Long id) {
        return find(id);
    }

    public List<EstadoActividad> findAllEstadoActividades() {
        return findAll();
    }
}
