import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { Venta } from '../_model/venta';
import { VentaDTO } from '../_model/ventaDTO';

@Injectable({
  providedIn: 'root'
})
export class VentaService {
  url: string = `${environment.HOST_URL}/ventas`;
  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<Venta[]>(this.url);
  }

  listarPorId(id: number) {
    return this.http.get<Venta>(`${this.url}/${id}`);
  }

  registrar(venta: VentaDTO) {
    return this.http.post(this.url, venta);
  }

  // blob indica que la respuesta va a estar en crudo (arreglo de bytes)
  generarReporte(ventaDTO: VentaDTO){
    return this.http.post(`${this.url}/generarReporte`, ventaDTO, {
      responseType: 'blob'
    });
  }

  /*listarResumen() {
    return this.http.get<ResumenVentaDTO[]>(`${this.url}/listarResumen`);
  }

  buscar(filtroConsulta: FiltroConsultaDTO) {
    return this.http.post<Venta[]>(`${this.url}/buscar`, filtroConsulta);
  }*/

  listarComidasPorVenta(idVenta: number){
    return this.http.get<VentaDTO[]>(`${this.url}/buscar/comidas/${idVenta}`);
  }

}
