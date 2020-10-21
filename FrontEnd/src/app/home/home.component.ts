import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'codehub-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private _router: Router) { }

  RegisterNavClick() {
    this._router.navigate(['/register'])
  }

  LoginNavClick() {
    this._router.navigate(['/login'])
  }
  

  ngOnInit(): void {
  }

}
