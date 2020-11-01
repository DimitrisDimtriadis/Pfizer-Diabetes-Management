import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MustMatch } from 'src/app/_helpers/must-match.validator';
import { FormBuilder, FormControl, FormGroup ,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { AdminService } from 'src/app/services/admin.service';
import { DoctorInactive } from 'src/app/classes/doctoInactive';


@Component({
  selector: 'app-admin-no-activity',
  templateUrl: './admin-no-activity.component.html',
  styleUrls: ['./admin-no-activity.component.scss']
})
export class AdminNoActivityComponent implements OnInit {

  mediData: DoctorInactive[];

  
    constructor(public admin:AdminService,private formBuilder: FormBuilder,private _router: Router) { }
    
    ngOnInit(): void {
    
    }
    
  
    Search() {
      this.admin.getInactiveDoctor().subscribe(
         data1=>{
           this.mediData=data1;
         }
      )
      alert("Show Inactive Doctors");
  }

}
