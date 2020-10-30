import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserClass } from 'src/app/classes/UserClass';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-doctor-nav',
  templateUrl: './doctor-nav.component.html',
  styleUrls: ['./doctor-nav.component.scss']
})
export class DoctorNavComponent implements OnInit {

  constructor(public Uservice:UserService,private _router: Router) { }
  userObj1:UserClass;
  userAmka:string;
  
  ngOnInit() {
    this.Uservice.getUserData().subscribe(
      data=>{
        this.userObj1=data;
          }
    );
    this.userAmka=String(this.userObj1?.amka);
    sessionStorage.setItem("amka",this.userAmka);

  }

  logout(){
    sessionStorage.setItem('LoginRole',"");
    this._router.navigate(['login']);
  }

}
