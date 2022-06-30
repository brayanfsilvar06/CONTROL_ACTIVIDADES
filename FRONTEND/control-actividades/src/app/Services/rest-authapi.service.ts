import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { RequestAuth } from "../Interfaces/Request/request-auth";
import { ResponseAuthapi } from "../Interfaces/Response/response-authapi";

@Injectable({
  providedIn: 'root'
})
export class RestAuthapiService {

  private apiURL = environment.URL_SERVICE + "/AuthAPI";
  public headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });
  constructor(private httpClient: HttpClient) { }

  authAPI() {
    let fullURL = this.apiURL;
    let requestAuthAPI: RequestAuth;
    requestAuthAPI = {
      sUser: 'CTRL_ACTIVIDADES_2022',
      sPass: 'ZWUxMzRkMzAtZjE3My00YWRiLWJkNmMtYTdlY2ZmZDNmY2M4'
    }
    return this.httpClient.post<ResponseAuthapi>(fullURL, requestAuthAPI, { headers: this.headers });
  }
}
