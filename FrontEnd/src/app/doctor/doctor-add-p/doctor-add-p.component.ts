import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-add-p',
  templateUrl: './doctor-add-p.component.html',
  styleUrls: ['./doctor-add-p.component.scss']
})
export class DoctorAddPComponent implements OnInit {

  constructor(private _router: Router) { }

  ngOnInit(): void {
  }

  logout(){
    sessionStorage.setItem('LoginRole',"");
    this._router.navigate(['login']);
  }

}
