import { Component, OnInit } from '@angular/core';
import { MustMatch } from 'src/app/_helpers/must-match.validator';
import { FormBuilder, FormControl, FormGroup ,Validators } from '@angular/forms';

@Component({
  selector: 'codehub-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm:FormGroup;
  submitted = false;
  
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.loginForm= this.formBuilder.group({
      email:['', [Validators.required, Validators.email, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    
    });
  }
  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  loginSumbit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.loginForm.invalid) {
          return;
      }

      alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.loginForm.value))
  }
}
