import { GeneroService } from './../../../_service/genero.service';
import { Genero } from './../../../_model/genero';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-genero-dialogo',
  templateUrl: './genero-dialogo.component.html',
  styleUrls: ['./genero-dialogo.component.css']
})
export class GeneroDialogoComponent implements OnInit {

  genero: Genero;

  constructor(public dialogRef: MatDialogRef<Genero>,
    @Inject(MAT_DIALOG_DATA) public data: Genero,
    private generoService: GeneroService) { }

  ngOnInit() {
    //this.genero = this.data;
    this.genero = new Genero();
    this.genero.idGenero = this.data.idGenero;
    this.genero.nombre = this.data.nombre;
  }

  operar(){
    if (this.genero.idGenero > 0) {
      //Modificar
      this.generoService.modificar(this.genero).subscribe(() => {
        this.generoService.listar().subscribe(data => {
          this.generoService.generoCambio.next(data);
          this.generoService.mensajeCambio.next('Se Modifico');
        });
      });
    } else{
      //Registrar
      this.generoService.registrar(this.genero).subscribe(()=> {
        this.generoService.listar().subscribe(data => {
          this.generoService.generoCambio.next(data);
          this.generoService.mensajeCambio.next('Se Registro');
        });
      });
    }
    this.dialogRef.close();
  }

  cancelar(){
    this.dialogRef.close();
  }
}
