import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { DoctorComponent } from './doctor/doctor.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PatientComponent } from './patient/patient.component';
import { RegisterComponent } from './register/register.component';



const routes: Routes = 
[
{path: '',  component: HomeComponent},
{path: 'home', component: HomeComponent},
{path: 'register', component: RegisterComponent},
{path:'login',component: LoginComponent},
{path:'patient',component: PatientComponent},
{path: 'doctor', component: DoctorComponent},
{path: 'admin', component: AdminComponent},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
