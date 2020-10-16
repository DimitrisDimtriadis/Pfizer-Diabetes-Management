import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PatientComponent } from './patient/patient.component';

const routes: Routes = 
[
  {path: '', component: LoginComponent},
  {path:'register',component: RegisterComponent},
  {path:'patient',component: PatientComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }