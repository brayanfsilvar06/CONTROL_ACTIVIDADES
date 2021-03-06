/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.service;

import com.co.brayanfsilvar.ctrlactividades.dao.ActividadDao;
import com.co.brayanfsilvar.ctrlactividades.domain.Actividad;
import com.co.brayanfsilvar.ctrlactividades.general.ApplicationLog;
import com.co.brayanfsilvar.ctrlactividades.general.Utilidad;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponseActividades;
import com.co.brayanfsilvar.ctrlactividades.model.response.ResponseGenerico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author BrayanFSilvaR
 */
public class ActividadService extends ApplicationLog {

    private ActividadDao actividadDao = null;
    private Utilidad util = null;
    private static final String ST_MSJ_ERROR_TECNICO = "SE HA PRESENTANDO UN ERROR INESPERADO. INTENTE DE NUEVO MÁS TARDE.";


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

    public List<Actividad> obtenerListadoActividades() {
        List<Actividad> listActividades = new ArrayList<>();
        List<Actividad> listActividadesTemp = this.findAllActividades();
        if (listActividadesTemp != null && !listActividadesTemp.isEmpty()) {
            for (Actividad actividad : listActividadesTemp) {
                actividad.setLMarcaTiempoEjecucionEstimada(actividad.getFFechaEstimadaEjecucion().getTime());
                Date fechaActual = new Date();
                Date fechaEjec = actividad.getFFechaEstimadaEjecucion();
                Long dias = Long.parseLong("0");
                if (fechaEjec.after(fechaActual)) {
                    actividad.setIDiasRetraso(dias);
                } else {
                    dias = (Long) ((fechaActual.getTime() - fechaEjec.getTime()) / 86400000);
                    actividad.setIDiasRetraso(dias);
                }
                listActividades.add(actividad);
            }
        }
        return listActividades;
    }

    public ResponseActividades obtenerActividades() {
        ResponseActividades responseActividades = new ResponseActividades();
        ResponseGenerico responseGenerico = new ResponseGenerico();
        try {
            List<Actividad> listActividades = this.findAllActividades();
            if (listActividades != null && !listActividades.isEmpty()) {
                responseGenerico.setbSuccess(Boolean.TRUE);
                responseGenerico.setsMsj("Se han obtenido las actividades");
                responseActividades.setListaEstadoActividades(null);
                responseActividades.setListaActividads(new ArrayList<Actividad>());
                responseActividades.setResponseGenerico(responseGenerico);
            } else {
                responseGenerico.setbSuccess(Boolean.FALSE);
                responseGenerico.setsMsj("No se han encontrado registro de actividades");
                responseActividades.setListaEstadoActividades(null);
                responseActividades.setListaActividads(listActividades);
                responseActividades.setResponseGenerico(responseGenerico);

            }
        } catch (Exception e) {
            logFatal(" obtenerActividades || " + this.util.obtenerMsjExcepcion(e));
            responseGenerico.setbSuccess(Boolean.FALSE);
            responseGenerico.setsMsj(ST_MSJ_ERROR_TECNICO);
            responseActividades.setResponseGenerico(responseGenerico);
        }
        return responseActividades;
    }

    public ResponseGenerico crearActividad(Actividad actividadNueva) {
        ResponseGenerico responseGenerico = new ResponseGenerico();
        try {
            if (actividadNueva != null && actividadNueva.getIPersonaAsignada() != null && actividadNueva.getIPersonaAsignada().getICodigo() != null && actividadNueva.getIEstadoActividad() != null && actividadNueva.getIEstadoActividad().getICodigo() != null) {
                Long marcaTiempoFechaEjecucion = actividadNueva.getLMarcaTiempoEjecucionEstimada() != null ? actividadNueva.getLMarcaTiempoEjecucionEstimada() : new Date().getTime();
                actividadNueva.setFFechaEstimadaEjecucion(new Date(marcaTiempoFechaEjecucion));
                Actividad actividadCreada = this.saveActividad(actividadNueva);
                if (actividadCreada != null && actividadCreada.getICodigo() != null) {
                    responseGenerico.setbSuccess(Boolean.TRUE);
                    responseGenerico.setsMsj("Se ha creado correctamente la actividad.");
                } else {
                    responseGenerico.setbSuccess(Boolean.FALSE);
                    responseGenerico.setsMsj("No se ha logrado crear la actividad.");
                }
            } else {
                responseGenerico.setbSuccess(Boolean.FALSE);
                responseGenerico.setsMsj("Algunos datos son necesarios para crear la actividad.");
            }
        } catch (Exception e) {
            logFatal(" crearActividad || " + this.util.obtenerMsjExcepcion(e));
            responseGenerico.setbSuccess(Boolean.FALSE);
            responseGenerico.setsMsj(ST_MSJ_ERROR_TECNICO);
        }
        return responseGenerico;
    }

    public ResponseGenerico actualizarActividad(Actividad actualizarActividad) {
        ResponseGenerico responseGenerico = new ResponseGenerico();
        try {
            if (actualizarActividad != null && actualizarActividad.getICodigo() != null) {
                Long marcaTiempoFechaEjecucion = actualizarActividad.getLMarcaTiempoEjecucionEstimada() != null ? actualizarActividad.getLMarcaTiempoEjecucionEstimada() : new Date().getTime();
                actualizarActividad.setFFechaEstimadaEjecucion(new Date(marcaTiempoFechaEjecucion));
                Actividad busquedaActividad = this.findActividadById(actualizarActividad.getICodigo());
                if (busquedaActividad != null && busquedaActividad.getICodigo() != null) {
                    Actividad actividadActualizada = this.updateActividad(actualizarActividad);
                    if (actividadActualizada != null && actividadActualizada.getICodigo() != null) {
                        responseGenerico.setbSuccess(Boolean.TRUE);
                        responseGenerico.setsMsj("Se ha actualizado correctamente la actividad.");
                    } else {
                        responseGenerico.setbSuccess(Boolean.FALSE);
                        responseGenerico.setsMsj("No se ha logrado actualizar la actividad.");
                    }
                } else {
                    responseGenerico.setbSuccess(Boolean.FALSE);
                    responseGenerico.setsMsj("No se ha logrado confirmar la información de la actividad a actualizar.");
                }

            } else {
                responseGenerico.setbSuccess(Boolean.FALSE);
                responseGenerico.setsMsj("Algunos datos son necesarios para actualizar la actividad.");
            }
        } catch (Exception e) {
            logFatal(" actualizarActividad || " + this.util.obtenerMsjExcepcion(e));
            responseGenerico.setbSuccess(Boolean.FALSE);
            responseGenerico.setsMsj(ST_MSJ_ERROR_TECNICO);
        }
        return responseGenerico;
    }

    public ResponseGenerico eliminarActividad(Actividad eliminarActividad) {
        ResponseGenerico responseGenerico = new ResponseGenerico();
        try {
            if (eliminarActividad != null && eliminarActividad.getICodigo() != null) {
                Actividad busquedaActividad = this.findActividadById(eliminarActividad.getICodigo());
                if (busquedaActividad != null && busquedaActividad.getICodigo() != null) {
                    this.deleteActividad(busquedaActividad);
                    responseGenerico.setbSuccess(Boolean.TRUE);
                    responseGenerico.setsMsj("Se ha eliminado correctamente la actividad.");
                } else {
                    responseGenerico.setbSuccess(Boolean.FALSE);
                    responseGenerico.setsMsj("No se ha logrado confirmar la información de la actividad a eliminar.");
                }
            } else {
                responseGenerico.setbSuccess(Boolean.FALSE);
                responseGenerico.setsMsj("Algunos datos son necesarios para eliminado la actividad.");
            }
        } catch (Exception e) {
            logFatal(" eliminarActividad || " + this.util.obtenerMsjExcepcion(e));
            responseGenerico.setbSuccess(Boolean.FALSE);
            responseGenerico.setsMsj(ST_MSJ_ERROR_TECNICO);
        }
        return responseGenerico;
    }
}
