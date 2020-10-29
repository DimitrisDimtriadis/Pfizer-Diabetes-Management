import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'codehub-list-data',
  templateUrl: './list-data.component.html',
  styleUrls: ['./list-data.component.scss']
})
export class ListDataComponent implements OnInit {
  form: FormGroup;

  constructor(private formBuilder: FormBuilder,private router: Router) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      fromDate: ['', Validators.required],
      untilDate: ['', Validators.required],
  });
  }

  logout(){
    sessionStorage.setItem('LoginRole',"");
    this.router.navigate(['login']);
  }
}
