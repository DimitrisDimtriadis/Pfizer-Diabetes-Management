import { Component, OnInit } from '@angular/core';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MustMatch } from 'src/app/_helpers/must-match.validator';
import { FormBuilder, FormControl, FormGroup ,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SubPatient } from '../classes/subPatient';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
     mediData: SubPatient[];
    form:FormGroup;
    submitted = false;
  
    constructor(public data:AdminService
      ,private formBuilder: FormBuilder,
      private router: Router) { }
    
    ngOnInit(): void {
      this.form= this.formBuilder.group({
      amka:[''],
      fromDate: [''],
      untilDate: [''],
      });
    }

    // convenience getter for easy access to form fields
    get f() { return this.form.controls; }
  
    Search() {
      this.submitted = true;
 
      // stop here if form is invalid
      if (this.form.invalid) {
          return;
      }
      let st =(<HTMLInputElement> document.getElementById('from')).value;
    this.data.subData.startAt = new Date(st).toISOString();

    let ed= (<HTMLInputElement>document.getElementById('until')).value;
    this.data.subData.endAt = new Date(ed).toISOString();
    this.data.subData.amka = this.form.get('amka').value;

    console.log(st);
    console.log(ed);
      
     this.data.getSubPatient(this.data.subData).subscribe(
        data1=>{
          this.mediData=data1;
        }
     )
     alert("show measurements complete");
  }
  
    numberOnly(event): boolean {
      const charCode = (event.which) ? event.which : event.keyCode;
      if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
      }
      return true;
  
    }

    logout(){
      sessionStorage.setItem('LoginRole',"");
      this.router.navigate(['login']);
    }
}
