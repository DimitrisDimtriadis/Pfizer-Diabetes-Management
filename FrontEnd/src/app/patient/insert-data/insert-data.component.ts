import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'codehub-insert-data',
  templateUrl: './insert-data.component.html',
  styleUrls: ['./insert-data.component.scss']
})
export class InsertDataComponent implements OnInit {
  insertform: FormGroup;
  submitted = false;
  
  constructor(private formBuilder: FormBuilder,private router: Router) { }

  ngOnInit(): void {
    this.insertform = this.formBuilder.group({
      carb: ['', Validators.required],
      glucose:['', Validators.required],
      measuredDate: [''],
    });
  }
  logout(){
    sessionStorage.setItem('LoginRole',"");
    this.router.navigate(['login']);
  }

 // convenience getter for easy access to form fields
 get f() { return this.insertform.controls; }

 InsertDataSumbit() {
     this.submitted = true;

     // stop here if form is invalid
     if (this.insertform.invalid) {
         return;
     }

     alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.insertform.value))
 }
  }

