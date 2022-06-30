/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.service;

import com.co.brayanfsilvar.ctrlactividades.dao.PersonaDao;
import com.co.brayanfsilvar.ctrlactividades.domain.Persona;
import com.co.brayanfsilvar.ctrlactividades.general.ApplicationLog;
import com.co.brayanfsilvar.ctrlactividades.general.Utilidad;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponseGenerico;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponsePersonas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BrayanFSilvaR
 */
public class PersonaService extends ApplicationLog {

    private PersonaDao personaDao = null;
    private Utilidad util = null;

    public PersonaService() {
        personaDao = new PersonaDao();
        util = new Utilidad();
    }

    private Persona savePersona(Persona newPersona) {
        return personaDao.savePersona(newPersona);
    }

    private Persona updatePersona(Persona updatePersona) {
        return personaDao.updatePersona(updatePersona);
    }

    private void deletePersona(Persona deletePersona) {
        personaDao.deletePersona(deletePersona);
    }

    private Persona findPersonaById(Long id) {
        return personaDao.findPersonaById(id);
    }

    private List<Persona> findAllPersonaes() {
        return personaDao.findAllPersonas();
    }

    private List<Persona> obtenerEmpleados() {
        return personaDao.listarPersonasEmpleados();
    }

    public ResponsePersonas listadoEmpleados() {
        ResponsePersonas responsePersonas = new ResponsePersonas();
        ResponseGenerico responseGenerico = new ResponseGenerico();
        try {
            List<Persona> listEmpleadosTemp = this.obtenerEmpleados();
            if (listEmpleadosTemp != null && !listEmpleadosTemp.isEmpty()) {
                List<Persona> listEmpleados = new ArrayList<>();
                for (Persona persona : listEmpleadosTemp) {
                    listEmpleados.add(persona);
                }
                responseGenerico.setbSuccess(Boolean.TRUE);
                responseGenerico.setsMsj("Se han obtenido los empleados de manera correcta.");
                responsePersonas.setResponseGenerico(responseGenerico);
                responsePersonas.setListaPersonas(listEmpleados);
            } else {
                responseGenerico.setbSuccess(Boolean.FALSE);
                responseGenerico.setsMsj("No se han encontrado empleados.");
                responsePersonas.setResponseGenerico(responseGenerico);
            }
        } catch (Exception e) {
            logFatal(" listadoEmpleados || " + this.util.obtenerMsjExcepcion(e));
            responseGenerico.setbSuccess(Boolean.FALSE);
            responseGenerico.setsMsj("SE HA PRESENTANDO UN ERROR INESPERADO. INTENTE DE NUEVO MÁS TARDE.");
            responsePersonas.setResponseGenerico(responseGenerico);
        }
        return responsePersonas;
    }
}
