import { EstadoActividad } from './../estado-actividad';
import { Actividad } from './../actividad';
import { ResponseGenerico } from './response-generico';
export interface ResponseActividades {
    actividad?: Actividad,
    listaActividades?: Actividad[],
    listaEstadoActividades: EstadoActividad[],
    responseGenerico: ResponseGenerico
}
