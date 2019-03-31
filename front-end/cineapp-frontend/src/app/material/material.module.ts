import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatTableModule, MatIconModule, MatPaginatorModule, MatSortModule, MatFormFieldModule, MatInputModule, MatDialogModule,
         MatSidenavModule, MatDividerModule, MatToolbarModule, MatMenuModule } from '@angular/material';

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
    MatMenuModule
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
    MatMenuModule
  ]
})
export class MaterialModule { }
