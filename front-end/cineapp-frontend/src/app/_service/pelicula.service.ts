import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { Pelicula } from '../_model/pelicula';

@Injectable({
  providedIn: 'root'
})
export class PeliculaService {
  url:string = `${environment.HOST_URL}/peliculas`;

  peliculaCambio = new Subject<Pelicula[]>();
  mensajeCambio = new Subject<string>();

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Pelicula[]>(this.url);
  }

  listarPorId(idPelicula: number){
    return this.http.get<Pelicula>(`${this.url}/${idPelicula}`);
  }

  registrar(pelicula: Pelicula){
    return this.http.post(this.url, pelicula);
  }

  modificar(pelicula: Pelicula){
    return this.http.put(this.url, pelicula);
  }

  eliminar(pelicula: Pelicula){
    return this.http.delete(`${this.url}/${pelicula.idPelicula}`);
  }
}
