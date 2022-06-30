import { ResponsePersona } from './../Interfaces/Response/response-persona';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RestPersonaService {

  private apiURL = environment.URL_SERVICE + "/Persona";
  public headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });

  constructor(private httpClient: HttpClient) { }

  listadoEmpleados(sAuthToken: any) {
    let fullURL = this.apiURL + "/listarEmpleados";
    this.headers = this.headers.set('Authorization', sAuthToken);
    return this.httpClient.get<ResponsePersona>(fullURL, { headers: this.headers });
  }
}
