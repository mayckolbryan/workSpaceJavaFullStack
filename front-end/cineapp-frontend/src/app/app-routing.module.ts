import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GeneroComponent } from './pages/genero/genero.component';
import { PeliculaComponent } from './pages/pelicula/pelicula.component';

const routes: Routes = [
  { path: 'genero', component: GeneroComponent },
  { path: 'pelicula', component: PeliculaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
