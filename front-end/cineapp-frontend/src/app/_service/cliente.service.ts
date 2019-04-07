import { Cliente } from './../_model/cliente';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  url: string = `${environment.HOST_URL}/clientes`;
  constructor(private http: HttpClient) { }
 
  listar() {
    return this.http.get<Cliente[]>(this.url);
  }
}
