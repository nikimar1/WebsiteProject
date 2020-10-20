import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule , FormGroup} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserConnectionService } from '../user-connection.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();

  constructor(private _service: UserConnectionService, private _router: Router) { }

  ngOnInit(): void {
  }

  loginUser(){
    this._service.loginUserHTTP(this.user).subscribe(
      data => {
        //this is me practicing and checking if my response object is ok and the lambda expression works. I don't actually need this as
        //upon failure a 401 http status is returned and there is an exception. later I will use this to set a parameter that stores the logged in user
        //so that we cannot login as the same user all over again. 
        if(data.success)
        {
          //console.log (data.user)
          //sessionStorage.setItem('currentUser', JSON.stringify(data.user))
          sessionStorage.setItem('currentUser', JSON.stringify(data.user))
          console.log("Login success", )
          console.log(JSON.stringify(data.user))
          console.log(data)
          this._router.navigate(['/loginsuccess'])
        }
      }, 
      error => console.log("exception occured")
    )
  }

}
