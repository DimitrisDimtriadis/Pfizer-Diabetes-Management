import { Component, OnInit } from '@angular/core';
import {PatientService} from '../services/patient.service';
import {PatientClass} from '../classes/patientClass';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {

  constructor(private pservice:PatientService,private _router: Router) { }
  listPatients:PatientClass[];
  listPatientsParam:PatientClass[];
  objToCapture:PatientClass;

  ngOnInit() {
    this.pservice.getPatients().subscribe(
      data=>{
        this.listPatients=data;
          }
    );

    this.pservice.getPatientsWithParams().subscribe(
      data=>{
        this.listPatientsParam=data;
          }
    );

var patient=new PatientClass();
patient.name="paris";
patient.email="vaslysalex@hotmial.gr";
patient.body="angular trials";

this.pservice.postPatients(patient).subscribe(
  data=>{
    this.objToCapture=data;
      }
);


  }

  

}
