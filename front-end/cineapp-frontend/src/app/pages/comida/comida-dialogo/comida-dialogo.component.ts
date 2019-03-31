import { ComidaService } from './../../../_service/comida.service';
import { Comida } from './../../../_model/comida';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-comida-dialogo',
  templateUrl: './comida-dialogo.component.html',
  styleUrls: ['./comida-dialogo.component.css']
})
export class ComidaDialogoComponent implements OnInit {

  comida: Comida;
  constructor(public dialogRef: MatDialogRef<Comida>,
    @Inject(MAT_DIALOG_DATA) public data: Comida,
    private comidaService: ComidaService) { }

  ngOnInit() {
    this.comida = new Comida();
    this.comida.idComida = this.data.idComida;
    this.comida.nombre = this.data.nombre;
    this.comida.precio = this.data.precio;
  }

  operar(){
    if (this.comida.idComida > 0) {
      //Modificar
      this.comidaService.modificar(this.comida).subscribe(() => {
        this.comidaService.listar().subscribe(data => {
          this.comidaService.comidaCambio.next(data);
          this.comidaService.mensajeCambio.next('Se Modifico');
        });
      });
    } else{
      //Registrar
      this.comidaService.registrar(this.comida).subscribe(()=> {
        this.comidaService.listar().subscribe(data => {
          this.comidaService.comidaCambio.next(data);
          this.comidaService.mensajeCambio.next('Se Registro');
        });
      });
    }
    this.dialogRef.close();
  }

  cancelar(){
    this.dialogRef.close();
  }
}
