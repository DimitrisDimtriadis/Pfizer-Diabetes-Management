import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientComponent } from './patient.component';
import { AppRoutingModule } from './patient-routing.module';
import { InsertDataComponent } from './insert-data/insert-data.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListDataComponent } from './list-data/list-data.component';
import { UpdateDataComponent } from './update-data/update-data.component';
import { AverageDataComponent } from './average-data/average-data.component';
import { ChartsDataComponent } from './charts-data/charts-data.component';
import { UpdateComponent } from './update/update.component';



@NgModule({
  declarations: [PatientComponent, InsertDataComponent, ListDataComponent, UpdateDataComponent, AverageDataComponent, ChartsDataComponent, UpdateComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  exports:[
    InsertDataComponent
  ]
})
export class PatientModule { }
