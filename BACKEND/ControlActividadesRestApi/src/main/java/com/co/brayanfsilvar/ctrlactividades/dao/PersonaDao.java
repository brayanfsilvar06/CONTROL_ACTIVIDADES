/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.dao;

import com.co.brayanfsilvar.ctrlactividades.domain.Persona;
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
public class PersonaDao extends GenericRepositoryJPA<Persona> {

    private EntityManager em = null;
    private static final String ST_S_EMPLEADO = "S";

    public Persona savePersona(Persona newPersona) {
        return create(newPersona);
    }

    public Persona updatePersona(Persona updatePersona) {
        return update(updatePersona);
    }

    public void deletePersona(Persona deletePersona) {
        delete(deletePersona);
    }

    public Persona findPersonaById(Long id) {
        return find(id);
    }

    public List<Persona> findAllPersonas() {
        return findAll();
    }

    public List<Persona> listarPersonasEmpleados() {
        List<Persona> listPersona = new ArrayList<>();
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            em = connDBJPA.getEntityManager();
            TypedQuery<Persona> consultDocument = em.createNamedQuery("Persona.findByCEmpleado", Persona.class);
            consultDocument.setParameter("cEmpleado", ST_S_EMPLEADO);
            listPersona = consultDocument.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            connDBJPA.closeEntityManager();
            closeEntityManager();
        }
        return listPersona;
    }
}
