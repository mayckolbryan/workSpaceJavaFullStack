import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatTableModule, MatIconModule, MatPaginatorModule, MatSortModule, MatFormFieldModule, MatInputModule, MatDialogModule,
         MatSidenavModule, MatDividerModule, MatToolbarModule, MatMenuModule, MatSnackBarModule, MatCardModule, MatDatepickerModule, 
         MatNativeDateModule, MatSelectModule, MAT_DATE_LOCALE, MatStepperModule, MatGridListModule, MatCheckboxModule, MatListModule, MatChipsModule } from '@angular/material';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatTableModule,
    MatIconModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    MatSidenavModule,
    MatDividerModule,
    MatToolbarModule,
    MatMenuModule,
    MatSnackBarModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,  //Para cambiar el idioma al calendar.
    MatSelectModule,
    MatStepperModule,
    MatGridListModule,
    MatCheckboxModule,
    MatListModule,
    MatChipsModule
  ],
  exports:[
    MatButtonModule,
    MatTableModule,
    MatIconModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    MatSidenavModule,
    MatDividerModule,
    MatToolbarModule,
    MatMenuModule,
    MatSnackBarModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatStepperModule,
    MatGridListModule,
    MatCheckboxModule,
    MatListModule,
    MatChipsModule
  ],
  providers:[
    { provide: MAT_DATE_LOCALE, useValue: 'es-ES' }
  ]
})
export class MaterialModule { }
