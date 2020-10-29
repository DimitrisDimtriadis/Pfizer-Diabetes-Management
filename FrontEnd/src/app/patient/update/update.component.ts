import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent implements OnInit {
  userForm:FormGroup;
  submitted = false;
  
  
  constructor(private formBuilder: FormBuilder,public userS:UserService, private router: Router) { }

  
  ngOnInit(): void {
    this.userForm= this.formBuilder.group({
      firstName:['', Validators.required],
      lastName: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      mobile:['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
      
     
    });
  }
  logout(){
    sessionStorage.setItem('LoginRole',"");
    this.router.navigate(['login']);
  }
  // convenience getter for easy access to form fields
  get f() { return this.userForm.controls; }

  RegisterSumbit() {
    this.submitted = true;

      // stop here if form is invalid
      if (this.userForm.invalid) {
          return;
      }
      alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.userForm.value))
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;

  }
}
