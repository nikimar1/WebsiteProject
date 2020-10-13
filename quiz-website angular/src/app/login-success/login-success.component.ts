import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import {TestService} from '../test.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-success',
  templateUrl: './login-success.component.html',
  styleUrls: ['./login-success.component.css']
})
export class LoginSuccessComponent implements OnInit {

  public sessionStorage = sessionStorage;
  testsDone: Observable<any>;
  testsPending: Observable<any>;

  constructor(private testService: TestService,  private _router: Router) { }
 
  ngOnInit() {
    this.reloadData();

   

  }
 
  onClick(test: String): void {
    console.log(test);
    this.testService.startTest(test);

    this._router.navigate(['/test'])
  }

  reloadData() {
    this.testsDone = this.testService.getTestsByCompletion(true);
    this.testsPending = this.testService.getTestsByCompletion(false);
  }
}
