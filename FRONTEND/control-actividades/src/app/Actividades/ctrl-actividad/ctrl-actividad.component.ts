import { ResponseGenerico } from './../../Interfaces/Response/response-generico';
import { ResponsePersona } from './../../Interfaces/Response/response-persona';
import { ResponseActividades } from './../../Interfaces/Response/response-actividades';
import { RestPersonaService } from './../../Services/rest-persona.service';
import { EstadoActividad } from './../../Interfaces/estado-actividad';
import { Persona } from './../../Interfaces/persona';
import { Component, OnInit } from '@angular/core';
import { Actividad } from './../../Interfaces/actividad';
import { ResponseAuthapi } from './../../Interfaces/Response/response-authapi';
import { RestActividadService } from './../../Services/rest-actividad.service';
import { RestAuthapiService } from './../../Services/rest-authapi.service';
import { MessageService } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-ctrl-actividad',
  templateUrl: './ctrl-actividad.component.html',
  styleUrls: ['./ctrl-actividad.component.css']
})
export class CtrlActividadComponent implements OnInit {

  actividades: Actividad[];
  actividad: Actividad;
  mostrarDialogActividad: boolean;
  activarBtnCrearActividad: boolean;
  activarBtnEditarActividad: boolean;
  showSpinner: boolean;
  showSpinnerDialog: boolean;
  estadoActividades: EstadoActividad[];
  empleados: Persona[];
  selectedEmpleado: number;
  selectedEstadoActividad: number;

  constructor(private restActividadService: RestActividadService,
    private restPersonaService: RestPersonaService,
    private restAuthApi: RestAuthapiService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) {
    this.actividades = [];
    this.estadoActividades = [];
    this.empleados = [];
    this.selectedEmpleado = 0;
    this.selectedEstadoActividad = 0;
    this.actividad = {
      IEstadoActividad: {
        ICodigo: 0,
        CDescripcion: ''
      },
      IPersonaAsignada: {
        ICodigo: 0,
        CLabelEmpleado: ''
      },
      FFechaEstimadaEjecucion: new Date(),
      LMarcaTiempoEjecucionEstimada: new Date().getTime()
    };
    this.mostrarDialogActividad = false;
    this.activarBtnCrearActividad = false;
    this.activarBtnEditarActividad = false;
    this.showSpinner = false;
    this.showSpinnerDialog = false;
  }



  ngOnInit(): void {
    this.showSpinner = true;
    this.authRestApi();
    this.obtenerActividades();
  }

  authRestApi() {
    this.messageService.clear();
    this.restAuthApi.authAPI().subscribe(
      (responseAuth: ResponseAuthapi) => {
        if (responseAuth !== null && responseAuth !== undefined) {
          if (responseAuth.bRta) {
            this.showSpinner = false;
            sessionStorage.setItem('sToken', 'Bearer' + responseAuth.sToken);
          } else {
            this.showSpinner = false;
            this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: responseAuth.sMsg, key: 'msg', life: 10000 });
          }
        } else {
          this.showSpinner = false;
          this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente al momento de contactar con la entidad, intente de nuevo.', key: 'msg', life: 10000 });
        }
      }, (error: any) => {
        this.showSpinner = false;
        console.error(error);
        this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente al momento de contactar con la entidad, intente de nuevo.', key: 'msg', life: 10000 });
      }
    );
  }

  obtenerEstadoActividades() {
    this.messageService.clear();
    this.estadoActividades = [];
    let sToken = sessionStorage.getItem('sToken');
    this.restActividadService.listadoEstadoActividades(sToken).subscribe(
      (responseActividades: ResponseActividades) => {
        if (responseActividades !== null && responseActividades !== undefined) {
          let responseGenerico = responseActividades.responseGenerico;
          if (responseGenerico?.bSuccess) {
            this.estadoActividades = responseActividades.listaEstadoActividades;
          } else {
            this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: responseGenerico.sMsj, key: 'msg', life: 10000 });
          }
        } else {
          this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente al momento de contactar con la entidad, intente de nuevo.', key: 'msg', life: 10000 });
        }
      }, (error: any) => {
        console.error(error);
        this.messageService.add({ severity: 'error', summary: '¡ERROR!', detail: 'Se ha presentado un inconveniente inesperado.', key: 'msg', life: 10000 });
      }
    );
  }

  obtenerEmpleados() {
    this.messageService.clear();
    this.empleados = [];
    let sToken = sessionStorage.getItem('sToken');
    this.restPersonaService.listadoEmpleados(sToken).subscribe(
      (responsePersona: ResponsePersona) => {
        if (responsePersona !== null && responsePersona !== undefined) {
          let responseGenerico = responsePersona.responseGenerico;
          if (responseGenerico?.bSuccess) {
            let empleados = responsePersona.listaPersonas;
            for (let value of empleados) {
              value.CLabelEmpleado = `${value.CNumeroIdentificacion}-${value.CNombres} ${value.CPrimerApellido}`;
              this.empleados.push(value);
            }
          } else {
            this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: responseGenerico.sMsj, key: 'msg', life: 10000 });
          }
        } else {
          this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente al momento de contactar con la entidad, intente de nuevo.', key: 'msg', life: 10000 });
        }
      }, (error: any) => {
        console.error(error);
        this.messageService.add({ severity: 'error', summary: '¡ERROR!', detail: 'Se ha presentado un inconveniente inesperado.', key: 'msg', life: 10000 });
      }
    );
  }

  obtenerActividades() {
    this.messageService.clear();
    this.actividades = [];
    let sToken = sessionStorage.getItem('sToken');
    this.restActividadService.listadoActividades(sToken).subscribe(
      (actividades: Actividad[]) => {
        if (actividades !== null && actividades !== undefined && actividades.length > 0) {

          for (let valor of actividades) {
            let empleadoAsignado = valor.IPersonaAsignada;
            empleadoAsignado.CLabelEmpleado = `${empleadoAsignado.CNombres} ${empleadoAsignado.CPrimerApellido}`;
            valor.IPersonaAsignada = empleadoAsignado;
            this.actividades.push(valor);
          }

        } else {
          this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'No se han obtenido registro de actividades', key: 'msg', life: 10000 });
        }
      }, (error: any) => {
        console.error(error);
        this.messageService.add({ severity: 'error', summary: '¡ERROR!', detail: 'Se ha presentado un inconveniente inesperado.', key: 'msg', life: 10000 });
      }
    );
  }

  inicializarEditarActividad(selectedActividad: Actividad) {
    this.messageService.clear();
    this.showSpinnerDialog = true;
    this.obtenerEmpleados();
    this.obtenerEstadoActividades();
    this.actividad = selectedActividad;
    this.selectedEmpleado = this.actividad.IPersonaAsignada.ICodigo;
    this.selectedEstadoActividad = this.actividad.IEstadoActividad.ICodigo;
    this.actividad.FFechaEstimadaEjecucion = new Date(this.actividad.FFechaEstimadaEjecucion);
    this.activarBtnCrearActividad = false;
    this.activarBtnEditarActividad = true;
    this.mostrarDialogActividad = true;
    this.showSpinnerDialog = false;
  }

  editarActividad() {
    this.messageService.clear();
    this.showSpinnerDialog = true;
    let modificarActividad = this.actividad;
    if (this.validarTituloRequerido(modificarActividad) &&
      this.validarDescActividadRequerido(modificarActividad) &&
      this.validarFechaEjecActividadRequerido(modificarActividad) &&
      this.validarEmpleadoActividadRequerido() &&
      this.validaEstadoActividadRequerido()) {
      let lMarcaTiempoEjecucionEstimada = modificarActividad.FFechaEstimadaEjecucion?.getTime();
      modificarActividad.LMarcaTiempoEjecucionEstimada = lMarcaTiempoEjecucionEstimada;
      modificarActividad.IPersonaAsignada = { ICodigo: this.selectedEmpleado };
      modificarActividad.IEstadoActividad = { ICodigo: this.selectedEstadoActividad, CDescripcion: '' };
      let sToken = sessionStorage.getItem('sToken');
      this.restActividadService.actualizarActividad(sToken, modificarActividad).subscribe(
        (responseGenerico: ResponseGenerico) => {
          if (responseGenerico !== null && responseGenerico !== undefined) {
            if (responseGenerico.bSuccess) {
              this.showSpinnerDialog = false;
              this.messageService.add({ severity: 'success', summary: '¡EXITO!', detail: responseGenerico.sMsj, key: 'msg', life: 10000 });
              this.obtenerActividades();
              this.ocultarDialogNuevaActividad();
            } else {
              this.showSpinnerDialog = false;
              this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: responseGenerico.sMsj, key: 'msg', life: 10000 });
            }
          } else {
            this.showSpinnerDialog = false;
            this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente al momento de contactar con la entidad, intente de nuevo.', key: 'msg', life: 10000 });
          }
        }, (error: any) => {
          this.showSpinnerDialog = false;
          console.error(error);
          this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente inesperado.', key: 'msg', life: 10000 });
        }
      );
    } else {
      this.showSpinnerDialog = false;
    }
  }

  eliminarActividad(selectedActividad: Actividad) {
    this.messageService.clear();
    this.showSpinner = true;
    if (selectedActividad !== null && selectedActividad !== undefined && selectedActividad.ICodigo !== null && selectedActividad.ICodigo !== undefined) {
      let sToken = sessionStorage.getItem('sToken');
      this.restActividadService.eliminarActividad(sToken, selectedActividad).subscribe(
        (responseGenerico: ResponseGenerico) => {
          if (responseGenerico !== null && responseGenerico !== undefined) {
            if (responseGenerico.bSuccess) {
              this.showSpinner = false;
              this.messageService.add({ severity: 'success', summary: '¡EXITO!', detail: responseGenerico.sMsj, key: 'msg', life: 10000 });
              this.obtenerActividades();
            } else {
              this.showSpinner = false;
              this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: responseGenerico.sMsj, key: 'msg', life: 10000 });
            }
          } else {
            this.showSpinner = false;
            this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente al momento de contactar con la entidad, intente de nuevo.', key: 'msg', life: 10000 });
          }
        }, (error: any) => {
          this.showSpinner = false;
          console.error(error);
          this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente inesperado.', key: 'msg', life: 10000 });
        }
      );
    } else {
      this.showSpinner = false;
      this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'No se ha seleccionado ninguna actividad.', key: 'msg', life: 10000 });
    }
  }

  confirmarEliminarActividad(selectedActividad: Actividad) {
    this.messageService.clear();
    this.confirmationService.confirm({
      message: '¿Esta seguro que desea eliminar? ' + selectedActividad.CTitulo + '?',
      header: 'Confirmar Eliminación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.eliminarActividad(selectedActividad);
        this.obtenerActividades();
      }
    });
  }

  abrirDialogNuevaActividad() {
    this.messageService.clear();
    this.showSpinnerDialog = true;
    this.obtenerEmpleados();
    this.obtenerEstadoActividades();
    this.actividad = {
      IEstadoActividad: {
        ICodigo: 0,
        CDescripcion: ''
      },
      IPersonaAsignada: {
        ICodigo: 0
      },
      FFechaEstimadaEjecucion: new Date(),
      LMarcaTiempoEjecucionEstimada: new Date().getTime()
    };
    this.activarBtnCrearActividad = true;
    this.activarBtnEditarActividad = false;
    this.mostrarDialogActividad = true;
    this.showSpinnerDialog = false;
  }

  crearActividad() {
    this.messageService.clear();
    this.showSpinnerDialog = true;
    let actividadNueva = this.actividad;
    if (this.validarTituloRequerido(actividadNueva) &&
      this.validarDescActividadRequerido(actividadNueva) &&
      this.validarFechaEjecActividadRequerido(actividadNueva) &&
      this.validarEmpleadoActividadRequerido() &&
      this.validaEstadoActividadRequerido()) {
      let lMarcaTiempoEjecucionEstimada = actividadNueva.FFechaEstimadaEjecucion?.getTime();
      actividadNueva.LMarcaTiempoEjecucionEstimada = lMarcaTiempoEjecucionEstimada;
      actividadNueva.IPersonaAsignada = { ICodigo: this.selectedEmpleado };
      actividadNueva.IEstadoActividad = { ICodigo: this.selectedEstadoActividad, CDescripcion: '' };
      let sToken = sessionStorage.getItem('sToken');
      this.restActividadService.crearActividad(sToken, actividadNueva).subscribe(
        (responseGenerico: ResponseGenerico) => {
          if (responseGenerico !== null && responseGenerico !== undefined) {
            if (responseGenerico.bSuccess) {
              this.showSpinnerDialog = false;
              this.messageService.add({ severity: 'success', summary: '¡EXITO!', detail: responseGenerico.sMsj, key: 'msg', life: 10000 });
              this.obtenerActividades();
              this.ocultarDialogNuevaActividad();
            } else {
              this.showSpinnerDialog = false;
              this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: responseGenerico.sMsj, key: 'msg', life: 10000 });
            }
          } else {
            this.showSpinnerDialog = false;
            this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente al momento de contactar con la entidad, intente de nuevo.', key: 'msg', life: 10000 });
          }
        }, (error: any) => {
          this.showSpinnerDialog = false;
          console.error(error);
          this.messageService.add({ severity: 'warn', summary: '¡ALERTA!', detail: 'Se ha presentado un inconveniente inesperado.', key: 'msg', life: 10000 });
        }
      );
    } else {
      this.showSpinnerDialog = false;
    }
  }

  ocultarDialogNuevaActividad() {
    this.activarBtnCrearActividad = true;
    this.activarBtnEditarActividad = false;
    this.mostrarDialogActividad = false;
  }

  validarTituloRequerido(actividadNueva: Actividad) {
    let pasoValidacion = true;
    if (actividadNueva.CTitulo == null && actividadNueva.CTitulo == undefined) {
      this.messageService.add({ severity: 'warn', summary: '¡Alerta!', detail: 'Debe ingresar el titulo de la actividad', key: 'msg', life: 10000 });
      pasoValidacion = false;
    }
    return pasoValidacion;
  }

  validarDescActividadRequerido(actividadNueva: Actividad) {
    let pasoValidacion = true;
    if (actividadNueva.CDescripcion == null && actividadNueva.CDescripcion == undefined) {
      this.messageService.add({ severity: 'warn', summary: '¡Alerta!', detail: 'Debe ingresar una descripción para la actividad', key: 'msg', life: 10000 });
      pasoValidacion = false;
    }
    return pasoValidacion;
  }

  validarFechaEjecActividadRequerido(actividadNueva: Actividad) {
    let pasoValidacion = true;
    if (actividadNueva.FFechaEstimadaEjecucion == null && actividadNueva.FFechaEstimadaEjecucion == undefined) {
      this.messageService.add({ severity: 'warn', summary: '¡Alerta!', detail: 'Debe ingresar la fecha de ejecución para la actividad', key: 'msg', life: 10000 });
      pasoValidacion = false;
    }
    return pasoValidacion;
  }

  validarEmpleadoActividadRequerido() {
    let pasoValidacion = true;
    if (this.selectedEmpleado == null && this.selectedEmpleado == undefined) {
      this.messageService.add({ severity: 'warn', summary: '¡Alerta!', detail: 'Debe seleccionar el empleado asignado ara la actividad', key: 'msg', life: 10000 });
      pasoValidacion = false;
    }
    return pasoValidacion;
  }

  validaEstadoActividadRequerido() {
    let pasoValidacion = true;
    if (this.selectedEstadoActividad == null && this.selectedEstadoActividad == undefined) {
      this.messageService.add({ severity: 'warn', summary: '¡Alerta!', detail: 'Debe seleccionar el estado de la actividad', key: 'msg', life: 10000 });
      pasoValidacion = false;
    }
    return pasoValidacion;
  }
}
