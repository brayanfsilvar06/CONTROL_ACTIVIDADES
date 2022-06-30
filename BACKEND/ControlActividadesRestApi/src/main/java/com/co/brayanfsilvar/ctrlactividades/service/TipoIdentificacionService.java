/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.service;

import com.co.brayanfsilvar.ctrlactividades.dao.TipoIdentificacionDao;
import com.co.brayanfsilvar.ctrlactividades.domain.TipoIdentificacion;
import com.co.brayanfsilvar.ctrlactividades.general.Utilidad;
import java.util.List;

/**
 *
 * @author BrayanFSilvaR
 */
public class TipoIdentificacionService {

    private TipoIdentificacionDao personaDao = null;
    private Utilidad util = null;

    public TipoIdentificacionService() {
        personaDao = new TipoIdentificacionDao();
        util = new Utilidad();
    }

    private TipoIdentificacion saveTipoIdentificacion(TipoIdentificacion newTipoIdentificacion) {
        return personaDao.saveTipoIdentificacion(newTipoIdentificacion);
    }

    private TipoIdentificacion updateTipoIdentificacion(TipoIdentificacion updateTipoIdentificacion) {
        return personaDao.updateTipoIdentificacion(updateTipoIdentificacion);
    }

    private void deleteTipoIdentificacion(TipoIdentificacion deleteTipoIdentificacion) {
        personaDao.deleteTipoIdentificacion(deleteTipoIdentificacion);
    }

    private TipoIdentificacion findTipoIdentificacionById(Long id) {
        return personaDao.findTipoIdentificacionById(id);
    }

    private List<TipoIdentificacion> findAllTipoIdentificaciones() {
        return personaDao.findAllTipoIdentificacions();
    }
}
