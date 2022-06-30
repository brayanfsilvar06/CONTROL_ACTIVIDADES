import { ResponseGenerico } from './response-generico';
import { Persona } from './../persona';
export interface ResponsePersona {
    persona: Persona,
    listaPersonas: Persona[],
    responseGenerico: ResponseGenerico
}
