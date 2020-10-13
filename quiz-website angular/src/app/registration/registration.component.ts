import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule , FormGroup} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserConnectionService } from '../user-connection.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user = new User();

  constructor(private _service: UserConnectionService, private _router: Router) { }

  ngOnInit(): void {
  }

  registerUser(){
    this._service.registerUserHTTP(this.user).subscribe(
      data => {
        console.log("Registration success", )
        this._router.navigate(['/registersuccess'])
      }, 
      error => console.log("exception occured")
    )

  }
}
