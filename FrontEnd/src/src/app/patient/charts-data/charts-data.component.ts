import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'codehub-charts-data',
  templateUrl: './charts-data.component.html',
  styleUrls: ['./charts-data.component.scss']
})
export class ChartsDataComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  logout(){
    sessionStorage.setItem('LoginRole',"");
    this.router.navigate(['login']);
  }
}
