import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { UserClass } from '../classes/UserClass';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {

  constructor(public Uservice:UserService,private _router: Router) { }
    userObj:UserClass;

  ngOnInit(): void {
    
  }

 
}
