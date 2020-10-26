import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {PatientComponent} from './patient/patient.component';
import {DoctorComponent} from './doctor/doctor.component';
import{AdminComponent} from './admin/admin.component';
import { DoctorAccComponent } from './doctor/doctor-acc/doctor-acc.component';
import { DoctorAddPComponent } from './doctor/doctor-add-p/doctor-add-p.component';
import { DoctorViewPComponent } from './doctor/doctor-view-p/doctor-view-p.component';
import { DoctorConsultPComponent } from './doctor/doctor-consult-p/doctor-consult-p.component';
import { AdminConsultWaitComponent } from './admin/admin-consult-wait/admin-consult-wait.component';
import { AdminNoActivityComponent } from './admin/admin-no-activity/admin-no-activity.component';
import { LogoutComponent } from './logout/logout.component';



const routes: Routes = 
[
{path: '', component: HomeComponent},
{path: 'home', component: HomeComponent},
{path: 'register', component: RegisterComponent},
{path:'login',component: LoginComponent},
{path:'patient',component: PatientComponent},
{path: 'doctor', component: DoctorComponent},
{path: 'admin', component: AdminComponent},
{path: 'doctorAcc', component: DoctorAccComponent},
{path: 'doctorAddP', component: DoctorAddPComponent},
{path: 'doctorViewP', component: DoctorViewPComponent},
{path: 'doctorConsultP', component: DoctorConsultPComponent},
{path: 'adminConW', component: AdminConsultWaitComponent},
{path: 'adminNoA', component: AdminNoActivityComponent},
{ path: 'logout', component: LogoutComponent},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
