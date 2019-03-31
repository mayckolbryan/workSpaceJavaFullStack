import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort, MatDialog } from '@angular/material';
import { Configuracion } from 'src/app/_model/configuracion';
import { ConfiguracionService } from 'src/app/_service/configuracion.service';
import { ConfiguracionDialogoComponent } from './configuracion-dialogo/configuracion-dialogo.component';

@Component({
  selector: 'app-configuracion',
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent implements OnInit {

  dataSource: MatTableDataSource<Configuracion>;
  displayedColumns: string[] = ['idConfig', 'parametro', 'valor', 'acciones'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private configuracionService: ConfiguracionService, private dialog: MatDialog) { }

  ngOnInit() {
    this.configuracionService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource<Configuracion>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

    this.configuracionService.configuracionCambio.subscribe(data => {
      this.dataSource = new MatTableDataSource<Configuracion>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  openDialog(configuracion?: Configuracion){
    let conf = configuracion != null ? configuracion : new Configuracion();
    this.dialog.open(ConfiguracionDialogoComponent, {
      data: conf,
      width: '250px'
    });
  }

  filter(filterValue: string){
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

  eliminar(configuracion: Configuracion){
    this.configuracionService.eliminar(configuracion).subscribe(() => {
      this.configuracionService.listar().subscribe(data => {
        this.configuracionService.configuracionCambio.next(data);
      });
    });
  }
}
