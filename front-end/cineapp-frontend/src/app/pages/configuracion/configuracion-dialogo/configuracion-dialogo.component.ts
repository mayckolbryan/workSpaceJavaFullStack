import { Component, OnInit, Inject } from '@angular/core';
import { Configuracion } from 'src/app/_model/configuracion';
import { ConfiguracionService } from 'src/app/_service/configuracion.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-configuracion-dialogo',
  templateUrl: './configuracion-dialogo.component.html',
  styleUrls: ['./configuracion-dialogo.component.css']
})
export class ConfiguracionDialogoComponent implements OnInit {

  configuracion: Configuracion;

  constructor(public dialogRef: MatDialogRef<Configuracion>,
              @Inject(MAT_DIALOG_DATA) public data: Configuracion,
              private configuracionService: ConfiguracionService) { }

  ngOnInit() {
    this.configuracion = new Configuracion();
    this.configuracion.idConfig = this.data.idConfig;
    this.configuracion.parametro = this.data.parametro;
    this.configuracion.valor = this.data.valor;
  }

  operar(){
    if (this.configuracion.idConfig > 0) {
      this.configuracionService.modificar(this.configuracion).subscribe(() => {
        this.configuracionService.listar().subscribe(data => {
          this.configuracionService.configuracionCambio.next(data);
        });
      });
    } else{
      this.configuracionService.registrar(this.configuracion).subscribe(() => {
        this.configuracionService.listar().subscribe(data => {
          this.configuracionService.configuracionCambio.next(data);
        });
      });
    }
    this.dialogRef.close();
  }

  cancelar(){
    this.dialogRef.close();
  }
}
