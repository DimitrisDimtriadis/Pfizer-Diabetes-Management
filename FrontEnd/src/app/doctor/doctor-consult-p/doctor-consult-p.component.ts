import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PatientRealClass } from 'src/app/classes/patientRealClass';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-doctor-consult-p',
  templateUrl: './doctor-consult-p.component.html',
  styleUrls: ['./doctor-consult-p.component.scss']
})
export class DoctorConsultPComponent implements OnInit {
  myPatient:PatientRealClass;
  constructor(private _router: Router,public Uservice:UserService) { }

  ngOnInit(): void {
    this.Uservice.currentId.userID=parseInt(sessionStorage.getItem("id"));
      this.Uservice.get1User(this.Uservice.currentId).subscribe(
        data=>{
          this.myPatient=data;
            }
      )

  }

 doctorViewP(id:Number){
  this.Uservice.currentId.userID=id;
  console.log(id);
  console.log(this.Uservice.currentId.userID);
  sessionStorage.setItem("getID",String(id));
  this._router.navigate(['/doctorViewP']);
 }

}
