import { MaterialModule } from './material/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GeneroComponent } from './pages/genero/genero.component';
import { PeliculaComponent } from './pages/pelicula/pelicula.component';
import { HttpClientModule } from '@angular/common/http';
import { GeneroDialogoComponent } from './pages/genero/genero-dialogo/genero-dialogo.component';
import { FormsModule } from '@angular/forms';
import { ConfiguracionComponent } from './pages/configuracion/configuracion.component';
import { ConfiguracionDialogoComponent } from './pages/configuracion/configuracion-dialogo/configuracion-dialogo.component';

@NgModule({
  declarations: [
    AppComponent,
    GeneroComponent,
    PeliculaComponent,
    GeneroDialogoComponent,
    ConfiguracionComponent,
    ConfiguracionDialogoComponent
  ],
  entryComponents:[
    GeneroDialogoComponent,
    ConfiguracionDialogoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
