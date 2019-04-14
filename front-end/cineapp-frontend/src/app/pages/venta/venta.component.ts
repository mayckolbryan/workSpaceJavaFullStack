import { ConfigService } from './../../_service/config.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ComidaService } from './../../_service/comida.service';
import { Comida } from './../../_model/comida';
import { PeliculaService } from './../../_service/pelicula.service';
import { ClienteService } from './../../_service/cliente.service';
import { Cliente } from './../../_model/cliente';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Pelicula } from 'src/app/_model/pelicula';
import { environment } from 'src/environments/environment';
import { Venta } from 'src/app/_model/venta';
import { DetalleVenta } from 'src/app/_model/detalleVenta';
import { VentaDTO } from 'src/app/_model/ventaDTO';
import { VentaService } from 'src/app/_service/venta.service';
import * as moment from 'moment';

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit {

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  tercerFormGroup: FormGroup;

  clientes: Cliente[];
  clienteSeleccionado: Cliente;
  peliculas: Pelicula[];
  peliculaSeleccionada: Pelicula;
  asientos: number[] = [];
  asientosSeleccionados: number[] = [];
  hidden: number;
  comidas: Comida[];
  comidasSeleccionadas: Comida[] = [];
  precioEntrada: number;
  precioTotal: number;

  constructor(private _formBuilder: FormBuilder, private clienteService: ClienteService, private peliculaService: PeliculaService,
              private comidaService: ComidaService, private sanitization: DomSanitizer, private configService: ConfigService,
              private ventaService: VentaService) {}

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.tercerFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

    this.listarClientes();
    this.listarPeliculas();
    this.listarComidas();

    this.asientosSeleccionados = [];
    for (let i = 1; i <= 100; i++) {
      this.asientos.push(i);
    }

    this.configService.leerParametro(environment.PRECIO_ENTRADA).subscribe(data => {
      this.precioEntrada = +data.valor;
    });
  }

  seleccionarPelicula(pelicula: Pelicula) {
    this.peliculaSeleccionada = pelicula;
  }

  listarPeliculas() {
    this.peliculaService.listar().subscribe(data => {
      this.peliculas = data;
    });
  }

  listarClientes(){
    this.clienteService.listar().subscribe(data => {
      this.clientes = data;
    });
  }

  seleccionarAsiento(asiento: number) {

    if (this.asientosSeleccionados.includes(asiento)) {
      //eliminando el asiento si ya esta seleccionado
      this.asientosSeleccionados.splice(this.asientosSeleccionados.indexOf(asiento), 1);
      //siempre guardo el tamaño de la lista de asientos seleccionados en un hidden
      this.hidden = this.asientosSeleccionados.length;
    } else {
      this.asientosSeleccionados.push(asiento);
      //siempre guardo el tamaño de la lista de asientos seleccionados en un hidden
      this.hidden = this.asientosSeleccionados.length;
    }
    this.precioTotal = this.precioEntrada * this.asientosSeleccionados.length;
  }

  listarComidas() {
    this.comidaService.listar().subscribe(data => {
      this.comidas = data;
      for (let c of this.comidas) {
        this.comidaService.listarPorId(c.idComida).subscribe(data => {

          let reader = new FileReader();
          reader.readAsDataURL(data);
          reader.onloadend = () => {
            let x = reader.result;
            c._foto = this.setear(x);
            c._isFoto = true;
          }
        });
      }
    });
  }

  //sanitization.bypassSecurityTrustResourceUrl --> Permite que el navegador no detecte como malicioso la cadena de la imagen(base 64)
  setear(x: any) {
    return this.sanitization.bypassSecurityTrustResourceUrl(x);
  }

  seleccionarComida(e: any, c: Comida) {
    if (e.checked) {
      this.comidasSeleccionadas.push(c);
      this.precioTotal = this.precioTotal + c.precio;
    } else {
      this.precioTotal = this.precioTotal - c.precio;
    }
  }

  verificar() {
    return !(this.asientosSeleccionados.length > 0 && this.clienteSeleccionado != null && this.peliculaSeleccionada != null);
  }

  registrar() {

    let venta = new Venta();
    venta.cliente = this.clienteSeleccionado;
    venta.fecha = moment().format('YYYY-MM-DDTHH:mm:ss');
    //https://stackoverflow.com/questions/10830357/javascript-toisostring-ignores-timezone-offset
    /*var tzoffset = (new Date()).getTimezoneOffset() * 60000; //offset in milliseconds
    var localISOTime = (new Date(Date.now() - tzoffset)).toISOString().slice(0, -1);
    console.log(localISOTime);*/
    venta.cantidad = this.asientosSeleccionados.length;
    venta.pelicula = this.peliculaSeleccionada;
    venta.total = this.precioTotal;

    let detalles: DetalleVenta[] = [];
    for (let a of this.asientosSeleccionados) {
      let detalle = new DetalleVenta();
      detalle.asiento = a;
      detalles.push(detalle);
    }
    venta.detalle = detalles;

    let ventaDTO = new VentaDTO();
    ventaDTO.venta = venta;
    ventaDTO.lstComidas = this.comidasSeleccionadas;
    this.ventaService.registrar(ventaDTO).subscribe(data => {
      if (data === 1) {
        /*this.snackBar.open('Se registro', 'AVISO', {
          duration: 2000
        });*/
        //generar impresion
        this.generarReporte(ventaDTO);
        //this.limpiar();
      }
    });
  }

  generarReporte(ventaDTO: VentaDTO) {
    this.ventaService.generarReporte(ventaDTO).subscribe(data => {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none;');
      a.href = url;
      a.download = 'venta.pdf';
      a.click();
    });
  }
}
