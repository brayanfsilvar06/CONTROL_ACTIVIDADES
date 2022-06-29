import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { ResponseActividades } from "../Interfaces/Response/response-actividades";
import { Actividad } from "../Interfaces/actividad";
@Injectable({
  providedIn: 'root'
})
export class RestActividadService {

  private apiURL = environment.URL_SERVICE + "/Actividad";
  public headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });
  
  constructor(private httpClient: HttpClient) { }

  listadoEstadoActividades(sAuthToken: string) {
    let fullURL = this.apiURL + "/listarEstadoActividades";
    this.headers = this.headers.set('Authorization', sAuthToken);
    return this.httpClient.get<ResponseActividades>(fullURL, { headers: this.headers });
  }

  listadoActividades(sAuthToken: string) {
    let fullURL = this.apiURL + "/obtenerListadoActividades";
    this.headers = this.headers.set('Authorization', sAuthToken);
    return this.httpClient.post<ResponseActividades>(fullURL, { headers: this.headers });
  }

  crearActividad(sAuthToken: string) {
    let fullURL = this.apiURL + "/crearActividad";
    this.headers = this.headers.set('Authorization', sAuthToken);
    return this.httpClient.post<ResponseActividades>(fullURL, null, { headers: this.headers });
  }

  actualizarActividad(sAuthToken: string, ctrlActividad: Actividad) {
    let fullURL = this.apiURL + "/actualizarActividad";
    this.headers = this.headers.set('Authorization', sAuthToken);
    return this.httpClient.post<ResponseActividades>(fullURL, ctrlActividad, { headers: this.headers });
  }

  eliminarActividad(sAuthToken: string, ctrlActividad: Actividad) {
    let fullURL = this.apiURL + "/eliminarActividad";
    this.headers = this.headers.set('Authorization', sAuthToken);
    return this.httpClient.post<ResponseActividades>(fullURL, ctrlActividad, { headers: this.headers });
  }
}
