import { Component, OnInit } from '@angular/core';
import {DoctorService} from '../services/doctor.service';
import {DoctorClass} from '../classes/doctorClass';
import { Router } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {

  constructor(public dservice:DoctorService,private _router: Router) { }

  ngOnInit(){
    this.getAllDoctors();
  }

  getAllDoctors() {
    this.dservice.getAllDoctors();
  }

  createOrUpdateDoctor(currentDoctor: DoctorClass) {
    if (currentDoctor.id === null) {
      this.createDoctor(currentDoctor);
    } else {
      this.updateDoctor(currentDoctor);
    }
  }

  createDoctor(doc: DoctorClass) {
    this.dservice.createDoctor(doc).subscribe(
      (result: DoctorClass) => {
        this.dservice.getAllDoctors();
        this.clearDoctor();
      });
  }

  updateDoctor(doc: DoctorClass) {
    this.dservice.updateDoctor(doc).subscribe(
      (result: DoctorClass) => {
        this.dservice.getAllDoctors();
        this.clearDoctor();
      });
  }

  clearDoctor() {
    this.dservice.currentDoctor = {
      id: null,
      name: '',
      email: '',
      body: ''
    };
  }


  editDoctor(doc: DoctorClass) {
    this.dservice.currentDoctor = Object.assign({}, doc);
  }

  deleteDoctor(id: number) {
    this.dservice.deleteDoctor(id).subscribe(
      (data) => {
        this.getAllDoctors();
      });
  }


}
