import { HttpClient } from '@angular/common/http';
import { Comida } from './../_model/comida';
import { Subject } from 'rxjs';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ComidaService {
  url:string = `${environment.HOST_URL}/comidas`;

  comidaCambio = new Subject<Comida[]>();
  mensajeCambio = new Subject<string>();

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Comida[]>(this.url);
  }

  listarPorId(comida: Comida){
    return this.http.get<Comida>(`${this.url}/${comida.idComida}`);
  }

  registrar(comida: Comida){
    return this.http.post(this.url, comida);
  }

  modificar(comida: Comida){
    return this.http.put(this.url, comida);
  }

  eliminar(comida: Comida){
    return this.http.delete(`${this.url}/${comida.idComida}`);
  }
}
