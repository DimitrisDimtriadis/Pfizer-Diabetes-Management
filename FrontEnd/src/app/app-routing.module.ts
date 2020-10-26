import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { PatientComponent } from './patient/patient.component';
import { RegisterComponent } from './register/register.component';



const routes: Routes = 
[
{path: '',  component: HomeComponent},
{path: 'home', component: HomeComponent},
{path: 'register', component: RegisterComponent},
{path:'login',component: LoginComponent},
{ path: 'logout', component: LogoutComponent},
{path:'patient',component: PatientComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
