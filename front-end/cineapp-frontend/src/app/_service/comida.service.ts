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

  // listarPorId(comida: Comida){
  //   return this.http.get<Comida>(`${this.url}/${comida.idComida}`);
  // }

  listarPorId(id: number) {
    return this.http.get(`${this.url}/${id}`, {
      responseType: 'blob'
    });
  }

  // registrar(comida: Comida){
  //   return this.http.post(this.url, comida);
  // }

  // modificar(comida: Comida){
  //   return this.http.put(this.url, comida);
  // }



  registrar(comida: Comida, file?: File) {
    let formdata: FormData = new FormData();
    formdata.append('file', file);

    const comidaBlob = new Blob([JSON.stringify(comida)], { type: "application/json" });
    formdata.append('comida', comidaBlob);

    return this.http.post(`${this.url}`, formdata, {
      responseType: 'text'
    });
  }

  modificar(comida: Comida, file?: File) {
    let formdata: FormData = new FormData();
    formdata.append('file', file);

    const comidaBlob = new Blob([JSON.stringify(comida)], { type: "application/json" });
    formdata.append('comida', comidaBlob);

    return this.http.put(`${this.url}`, formdata, {
      responseType: 'text'
    });
  }

  eliminar(comida: Comida){
    return this.http.delete(`${this.url}/${comida.idComida}`);
  }
}
