/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.model.response;

import com.co.brayanfsilvar.ctrlactividades.domain.Actividad;
import com.co.brayanfsilvar.ctrlactividades.domain.EstadoActividad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BrayanFSilvaR
 */
public class ResponseActividades {

    private Actividad actividad = null;
    private List<Actividad> listaActividades = null;
    private List<EstadoActividad> listaEstadoActividades = null;
    private ResponseGenerico responseGenerico;

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad persona) {
        this.actividad = persona;
    }

    public List<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividads(List<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public List<EstadoActividad> getListaEstadoActividades() {
        return listaEstadoActividades;
    }

    public void setListaEstadoActividades(List<EstadoActividad> listaEstadoActividades) {
        this.listaEstadoActividades = listaEstadoActividades;
    }

    public ResponseGenerico getResponseGenerico() {
        return responseGenerico;
    }

    public void setResponseGenerico(ResponseGenerico responseGenerico) {
        this.responseGenerico = responseGenerico;
    }

}
