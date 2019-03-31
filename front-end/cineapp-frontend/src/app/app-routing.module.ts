import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GeneroComponent } from './pages/genero/genero.component';
import { PeliculaComponent } from './pages/pelicula/pelicula.component';
import { ConfiguracionComponent } from './pages/configuracion/configuracion.component';

const routes: Routes = [
  { path: 'genero', component: GeneroComponent },
  { path: 'pelicula', component: PeliculaComponent },
  { path: 'configuracion', component: ConfiguracionComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
