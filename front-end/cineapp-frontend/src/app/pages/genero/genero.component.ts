import { GeneroDialogoComponent } from './genero-dialogo/genero-dialogo.component';
import { Genero } from './../../_model/genero';
import { GeneroService } from './../../_service/genero.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort, MatDialog, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-genero',
  templateUrl: './genero.component.html',
  styleUrls: ['./genero.component.css']
})
export class GeneroComponent implements OnInit {

  dataSource: MatTableDataSource<Genero>;
  displayedColumns: string[] = ['idGenero', 'nombre', 'acciones'];
  //ViewChild obtiene un elemento del html en typeScript para poder manipularlo.
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort:MatSort;

  constructor(private generoService : GeneroService, private dialog : MatDialog, private snackBar: MatSnackBar) { }

  //ngOnInit es como el postConstructor que se ejecuta despues del constructor, ya que la inyeccion de dependencias se ejecuta despues del constructor
  ngOnInit() {
    this.generoService.listar().subscribe(data => {
      console.log(data);
      this.dataSource = new MatTableDataSource<Genero>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

    //Estos son los Observabes, pendiente de los cambios de los sujetos (llamadas next()).
    this.generoService.mensajeCambio.subscribe(msg => {
      this.snackBar.open(msg, 'INFO', {
        duration: 2000
      });
    }) 

    this.generoService.generoCambio.subscribe(data => {
      this.dataSource = new MatTableDataSource<Genero>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  filter(filterValue: string){
    filterValue= filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

  openDialog(genero? : Genero){
    let gen = genero != null ? genero : new Genero();
    this.dialog.open(GeneroDialogoComponent, {
      data: gen,
      width: '250px'
    });
  }

  eliminar(genero: Genero){
    this.generoService.eliminar(genero).subscribe(() => {
      this.generoService.listar().subscribe(data => {
        //Con los next se envian cambios a los Subjects
        this.generoService.generoCambio.next(data);
        this.generoService.mensajeCambio.next('Se Eliminó');
      });
    });

    // this.snackBar.open('SE ELIMINO', 'INFO', {
    //   duration: 2000
    // });
  }
}
