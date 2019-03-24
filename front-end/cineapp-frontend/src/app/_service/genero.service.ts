import { Genero } from './../_model/genero';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeneroService {
  url:string = `${environment.HOST_URL}/generos`;
  
  generoCambio = new Subject<Genero[]>();

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Genero[]>(this.url);
  }

  listarPorId(genero: Genero){
    return this.http.get<Genero>(`${environment.HOST_URL}/${genero.idGenero}`);
  }

  registrar(genero: Genero){
    return this.http.post(this.url, genero);
  }

  modificar(genero: Genero){
    return this.http.put(this.url, genero);
  }

  eliminar(genero: Genero){
    return this.http.delete(`${environment.HOST_URL}/${genero.idGenero}`);
  }
}
