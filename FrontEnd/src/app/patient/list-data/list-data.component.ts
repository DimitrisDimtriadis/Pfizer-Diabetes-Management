import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Measurements } from 'src/app/classes/measurements';
import { MeasurementsService } from 'src/app/services/measurements.service';
import { DatePipe } from '@angular/common';
import { PostData } from 'src/app/classes/postData';


@Component({
  selector: 'codehub-list-data',
  templateUrl: './list-data.component.html',
  styleUrls: ['./list-data.component.scss']
})
export class ListDataComponent implements OnInit {
  form: FormGroup;
  mediData: PostData[];

  mediDataId:any;
  submitted = false;

  carbIntake: number[] = [];
  bloodGlucoseLevel:number[] = [];
  measurementDate: string[] = [];


  constructor(private formBuilder: FormBuilder,
    public data:MeasurementsService, private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.data.postData.userID= this.form.get('userID').value;
   this.data.getMeasurementsData().subscribe(medi => {
     this.mediData = medi, this.fillData(this.mediData)});
      
    this.form = this.formBuilder.group({
      fromDate: ['', Validators.required],
      untilDate: ['', Validators.required],
  });
  
}

fillData(medi){
  if(medi === undefined || medi.length == 0){
      this.carbIntake = [];
      this.bloodGlucoseLevel = [];
      this.measurementDate = [];  
  }else{

    const datePipe = new DatePipe('en-US');
    medi.forEach((value) => {
    this.carbIntake.push(value.carbIntake),
    this.bloodGlucoseLevel.push(value.bloodGlucoseLevel),
    this.measurementDate.push(datePipe.transform(value.measurementDate, 'EEEE, MMMM d'));
    }
    )
  }  
}

  logout(){
    sessionStorage.setItem('LoginRole',"");
    this.router.navigate(['login']);
  }
  // convenience getter for easy access to form fields
 get f() { return this.form.controls; }

 formSumbit() {
     this.submitted = true;

     // stop here if form is invalid
     if (this.form.invalid) {
         return;
     }
 }
}
