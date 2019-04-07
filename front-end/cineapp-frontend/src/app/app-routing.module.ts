import { VentaComponent } from './pages/venta/venta.component';
import { ComidaComponent } from './pages/comida/comida.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GeneroComponent } from './pages/genero/genero.component';
import { PeliculaComponent } from './pages/pelicula/pelicula.component';
import { ConfiguracionComponent } from './pages/configuracion/configuracion.component';
import { PeliculaEdicionComponent } from './pages/pelicula/pelicula-edicion/pelicula-edicion.component';

const routes: Routes = [
  { path: 'genero', component: GeneroComponent },
  { path: 'pelicula', component: PeliculaComponent, children: [
    {path: 'nuevo', component: PeliculaEdicionComponent},
    {path: 'edicion/:id', component: PeliculaEdicionComponent}
  ] },
  { path: 'configuracion', component: ConfiguracionComponent },
  { path: 'comida', component: ComidaComponent },
  { path: 'venta', component: VentaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
