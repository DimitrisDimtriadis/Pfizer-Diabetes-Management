import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  
public showMyMessage=false;
  constructor(
    private _router: Router
    ) { }

  ngOnInit(): void {
  }
  LoginNavClick() {
    this._router.navigate(['/'])
  }

  RegisterClick() {
    this.showMyMessage=true;
    
  }
}
