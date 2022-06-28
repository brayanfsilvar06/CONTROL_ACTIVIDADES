/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.dao;

import com.co.brayanfsilvar.ctrlactividades.domain.TipoIdentificacion;
import com.co.brayanfsilvar.ctrlactividades.general.ConnectionDBJPA;
import com.co.brayanfsilvar.ctrlactividades.general.GenericRepositoryJPA;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author BrayanFSilvaR
 */
public class TipoIdentificacionDao extends GenericRepositoryJPA<TipoIdentificacion> {

    private EntityManager em = null;
    private static final String ST_S_EMPLEADO = "S";

    public TipoIdentificacion saveTipoIdentificacion(TipoIdentificacion newTipoIdentificacion) {
        return create(newTipoIdentificacion);
    }

    public TipoIdentificacion updateTipoIdentificacion(TipoIdentificacion updateTipoIdentificacion) {
        return update(updateTipoIdentificacion);
    }

    public void deleteTipoIdentificacion(TipoIdentificacion deleteTipoIdentificacion) {
        delete(deleteTipoIdentificacion);
    }

    public TipoIdentificacion findTipoIdentificacionById(Long id) {
        return find(id);
    }

    public List<TipoIdentificacion> findAllTipoIdentificacions() {
        return findAll();
    }

    public List<TipoIdentificacion> listarTipoIdentificacionsEmpleados() throws Exception {
        List<TipoIdentificacion> listTipoIdentificacion = new ArrayList<>();
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            em = connDBJPA.getEntityManager();
            TypedQuery<TipoIdentificacion> consultDocument = em.createNamedQuery("TipoIdentificacion.findByCEmpleado", TipoIdentificacion.class);
            consultDocument.setParameter("cEmpleado", ST_S_EMPLEADO);
            listTipoIdentificacion = consultDocument.getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            connDBJPA.closeEntityManager();
            closeEntityManager();
        }
        return listTipoIdentificacion;
    }
}
