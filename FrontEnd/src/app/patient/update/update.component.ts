import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserClass } from 'src/app/classes/UserClass';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent implements OnInit {
  userForm:FormGroup;
  submitted = false;
  userObj:UserClass;
  genders = ['NA','FEMALE', 'MALE']
  roles = ['ROLE_PATIENT']

  constructor(private formBuilder: FormBuilder,
    public Uservice:UserService,
    public userS:UserService, private router: Router) { }

  
  ngOnInit(): void {

    this.Uservice.getUserData().subscribe(
      data=>{
        this.userObj=data;
          }
    );

    
      this.userForm= this.formBuilder.group({
        firstName:['', Validators.required],
        lastName: ['', Validators.required],
        address: [''],
        email:['', [Validators.required, Validators.email, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
        gender:['', Validators.required],
        typeAccount:['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(6)]],
        mobile:['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
        phone:[''],
        amka:['', [Validators.required, Validators.minLength(9),Validators.maxLength(9)]]
      }
      );
    }
     
  // convenience getter for easy access to form fields
  get f() { return this.userForm.controls; }

  Sumbit() {
    this.submitted = true;

      // stop here if form is invalid
      if (this.userForm.invalid) {
          return;
      }

      this.userS.currentUser.first_name = this.userForm.get('firstName').value;
      this.userS.currentUser.last_name=this.userForm.get('lastName').value;
      this.userS.currentUser.address=this.userForm.get('address').value;
      this.userS.currentUser.email=this.userForm.get('email').value;
      this.userS.currentUser.mobile_phone_number=this.userForm.get('mobile').value;
      this.userS.currentUser.phone_number=this.userForm.get('phone').value;

      if(this.userForm.get('gender').value=="MALE")
      {
        this.userS.currentUser.gender=1;
      }else if(this.userForm.get('gender').value=="FEMALE")
       {this.userS.currentUser.gender=2;}
       else this.userS.currentUser.gender=0;

      if(this.userForm.get('typeAccount').value=="ROLE_DOCTOR")
      {
        this.userS.currentUser.accountType=2;
      }else if(this.userForm.get('typeAccount').value=="ROLE_PATIENT") 
      {this.userS.currentUser.accountType=3;}
      else this.userS.currentUser.accountType=1;


      this.userS.currentUser.amka=this.userForm.get('amka').value;
      this.userS.currentUser.password=this.userForm.get('password').value;
      
      console.log(this.userS.currentUser)
      this.userS.editUserData(this.userS.currentUser).subscribe(
        (response) => console.log(response),
            (error) => console.log(error));
           // sessionStorage.setItem("credentials",  this.userS.currentUser.email + ":" + this.userS.currentUser.password)
           // sessionStorage.setItem("modified", "false")
            alert('Your Data has been Updated!!');
            this.router.navigate(['login']);
            
      
  }

deletePatient(){
  this.Uservice.deleteUser().subscribe();
  alert("Delete User Success");
  this.router.navigate(['/login'])
}

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;

  }
}
