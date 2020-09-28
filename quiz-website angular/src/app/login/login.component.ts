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
        if(data.success)
        {
          console.log("Login success", )
          this._router.navigate(['/loginsuccess'])
        }
        else
        {
          console.log("Login failed")
        }
      }, 
      error => console.log("exception occured")
    )
  }

}
