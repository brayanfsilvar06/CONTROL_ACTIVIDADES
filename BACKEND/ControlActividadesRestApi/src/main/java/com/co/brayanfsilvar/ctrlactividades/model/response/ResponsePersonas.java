/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.model.response;

import com.co.brayanfsilvar.ctrlactividades.domain.Persona;
import java.util.List;

/**
 *
 * @author BrayanFSilvaR
 */
public class ResponsePersonas {

    private Persona persona;
    private List<Persona> listaPersonas;
    private ResponseGenerico responseGenerico;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public ResponseGenerico getResponseGenerico() {
        return responseGenerico;
    }

    public void setResponseGenerico(ResponseGenerico responseGenerico) {
        this.responseGenerico = responseGenerico;
    }

    
}
