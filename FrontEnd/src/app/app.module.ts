import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PatientComponent } from './patient/patient.component';
import { HttpClientModule } from '@angular/common/http';
import { PatientService } from './services/patient.service';
import { DoctorComponent } from './doctor/doctor.component';
import { DoctorService } from './services/doctor.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminComponent } from './admin/admin.component';






@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    PatientComponent,
    DoctorComponent,
    AdminComponent
 
  
  
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
    
    ],

  providers: [PatientService,DoctorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
