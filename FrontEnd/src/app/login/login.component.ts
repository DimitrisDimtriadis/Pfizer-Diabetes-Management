import { Component, OnInit } from '@angular/core';
import { MustMatch } from 'src/app/_helpers/must-match.validator';
import { FormBuilder, FormControl, FormGroup ,Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'codehub-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm:FormGroup;
  submitted = false;
  
  constructor(private formBuilder: FormBuilder,private _router: Router) { }

  ngOnInit(): void {
    this.loginForm= this.formBuilder.group({
      userName:['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
    
    },);
  }
  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.loginForm.invalid) {
          return;
      }

      alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.loginForm.value))
      this._router.navigate(['/patient'])
  }
}
