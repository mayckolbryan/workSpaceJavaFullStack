import { MaterialModule } from './material/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GeneroComponent } from './pages/genero/genero.component';
import { PeliculaComponent } from './pages/pelicula/pelicula.component';
import { HttpClientModule } from '@angular/common/http';
import { GeneroDialogoComponent } from './pages/genero/genero-dialogo/genero-dialogo.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ConfiguracionComponent } from './pages/configuracion/configuracion.component';
import { ConfiguracionDialogoComponent } from './pages/configuracion/configuracion-dialogo/configuracion-dialogo.component';
import { PeliculaEdicionComponent } from './pages/pelicula/pelicula-edicion/pelicula-edicion.component';
import { ComidaComponent } from './pages/comida/comida.component';
import { ComidaDialogoComponent } from './pages/comida/comida-dialogo/comida-dialogo.component';
import { VentaComponent } from './pages/venta/venta.component';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [
    AppComponent,
    GeneroComponent,
    PeliculaComponent,
    GeneroDialogoComponent,
    ConfiguracionComponent,
    ConfiguracionDialogoComponent,
    PeliculaEdicionComponent,
    ComidaComponent,
    ComidaDialogoComponent,
    VentaComponent
  ],
  entryComponents:[
    GeneroDialogoComponent,
    ConfiguracionDialogoComponent,
    ComidaDialogoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,  //Para trabajar con ngModels.
    ReactiveFormsModule, //Para trabajar con formularios.
    FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
