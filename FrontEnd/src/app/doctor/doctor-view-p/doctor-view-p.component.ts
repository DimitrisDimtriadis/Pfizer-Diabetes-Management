import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Chart} from '../../../../node_modules/chart.js/dist/chart.js';

@Component({
  selector: 'app-doctor-view-p',
  templateUrl: './doctor-view-p.component.html',
  styleUrls: ['./doctor-view-p.component.scss']
})
export class DoctorViewPComponent implements OnInit {
  chart=[];

  constructor(private _router: Router) { }

  ngOnInit(): void {

    this.chart = new Chart('myChart', {
      type: 'line',
      data: {
          labels: ['October','November','December'],
          datasets: [{
              label: 'Sugar intake ',
              data: [12, 19, 3],
              backgroundColor: [
                  'rgba(255, 99, 132, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
                  'rgba(255, 206, 86, 0.2)'
                 
              ],
              borderColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)'
                 
              ],
              borderWidth: 1
          }]
      },
      options: {
          scales: {
              yAxes: [{
                  ticks: {
                      beginAtZero: true
                  }
              }]
          }
      }
  });

  }

  logout(){
    sessionStorage.setItem('LoginRole',"");
    this._router.navigate(['login']);
  }

}
