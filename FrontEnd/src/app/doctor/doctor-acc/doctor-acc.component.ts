import { Component, OnInit } from '@angular/core';
import { MustMatch } from 'src/app/_helpers/must-match.validator';
import { FormBuilder, FormControl, FormGroup ,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserClass } from 'src/app/classes/UserClass';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-doctor-acc',
  templateUrl: './doctor-acc.component.html',
  styleUrls: ['./doctor-acc.component.scss']
})
export class DoctorAccComponent implements OnInit {

  userForm:FormGroup;
  submitted = false;
  genders = ['NA','FEMALE', 'MALE']
  userObj:UserClass;
  constructor(private formBuilder: FormBuilder,private _router: Router,public Uservice:UserService) { }
  
  ngOnInit(): void {

    this.Uservice.getUserData().subscribe(
      data=>{
        this.userObj=data;
          }
    );

    this.userForm= this.formBuilder.group({
      firstName:['', Validators.required],
      lastName: ['', Validators.required],
  
      gender:['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword:['', Validators.required],
      address: [''],
      mobile:['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
      phone:['', [Validators.required, Validators.minLength(10)]]
      
    }, {
      validator: MustMatch('password', 'confirmPassword')
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

      alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.userForm.value))
      this._router.navigate(['/doctor'])
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;

  }

 

}
