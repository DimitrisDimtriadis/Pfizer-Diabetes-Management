import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MustMatch } from 'src/app/_helpers/must-match.validator';
import { FormBuilder, FormControl, FormGroup ,Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-no-activity',
  templateUrl: './admin-no-activity.component.html',
  styleUrls: ['./admin-no-activity.component.scss']
})
export class AdminNoActivityComponent implements OnInit {

  userForm:FormGroup;
    submitted = false;
  
    constructor(private formBuilder: FormBuilder,private _router: Router) { }
    
    ngOnInit(): void {
      this.userForm= this.formBuilder.group({
       
        email:['', [Validators.required, Validators.email]],
        dEnd :['', Validators.required],
        dStart :['', Validators.required]
      });
    }
    // convenience getter for easy access to form fields
    get f() { return this.userForm.controls; }
  
    onSubmit() {
        this.submitted = true;
  
        // stop here if form is invalid
        if (this.userForm.invalid) {
            return;
        }
  
       
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
      this._router.navigate(['login']);
    }

}
