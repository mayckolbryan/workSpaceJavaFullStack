import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Subject } from 'rxjs';
import { Configuracion } from '../_model/configuracion';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConfiguracionService {
  url:string = `${environment.HOST_URL}/configuraciones`;

  configuracionCambio = new Subject<Configuracion[]>();

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Configuracion[]>(this.url);
  }

  listarPorId(configuracion: Configuracion){
    return this.http.get<Configuracion>(`${this.url}/${configuracion.idConfig}`);
  }

  registrar(configuracion: Configuracion){
    return this.http.post(this.url, configuracion);
  }

  modificar(configuracion: Configuracion){
    return this.http.put(this.url, configuracion);
  }

  eliminar(configuracion: Configuracion){
    return this.http.delete(`${this.url}/${configuracion.idConfig}`);
  }
}
