import { ComidaDialogoComponent } from './comida-dialogo/comida-dialogo.component';
import { ComidaService } from './../../_service/comida.service';
import { Comida } from './../../_model/comida';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatDialog, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-comida',
  templateUrl: './comida.component.html',
  styleUrls: ['./comida.component.css']
})
export class ComidaComponent implements OnInit {

  dataSource: MatTableDataSource<Comida>;
  displayedColumns: string[] = ['idComida', 'nombre', 'precio', 'acciones'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private comidaService: ComidaService, private dialog : MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.comidaService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

    this.comidaService.mensajeCambio.subscribe(msg => {
      this.snackBar.open(msg, 'INFO', {
        duration: 2000
      });
    });

    this.comidaService.comidaCambio.subscribe(data => {
      this.dataSource = new MatTableDataSource<Comida>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  filter(filterValue: string){
    filterValue= filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

  openDialog(comida? : Comida){
    let com = comida != null ? comida : new Comida();
    this.dialog.open(ComidaDialogoComponent, {
      data: com,
      width: '250px'
    });
  }

  eliminar(comida: Comida){
    this.comidaService.eliminar(comida).subscribe(() => {
      this.comidaService.listar().subscribe(data => {
        //Con los next se envian cambios a los Subjects
        this.comidaService.comidaCambio.next(data);
        this.comidaService.mensajeCambio.next('Se Eliminó');
      });
    });

    // this.snackBar.open('SE ELIMINO', 'INFO', {
    //   duration: 2000
    // });
  }
}
