import { Component, OnInit } from '@angular/core';
import {DoctorService} from '../services/doctor.service';
import {DoctorClass} from '../classes/doctorClass';
import { Router } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../services/user.service';
import { UserClass } from '../classes/UserClass';


@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {

  constructor(public Uservice:UserService,private _router: Router) { }
    userObj:UserClass;


  ngOnInit(){
    this.Uservice.getUserData().subscribe(
      data=>{
        this.userObj=data;
          }
    );
  }

  logout(){
    sessionStorage.setItem('LoginRole',"");
    this._router.navigate(['login']);
  }


}
