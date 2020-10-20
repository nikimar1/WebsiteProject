import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-score-page',
  templateUrl: './score-page.component.html',
  styleUrls: ['./score-page.component.css']
})
export class ScorePageComponent implements OnInit {

  constructor( private _router: Router) { }

  testScore:String;
  testTitle:String;

  ngOnInit(): void {

    this.testScore= sessionStorage.getItem("currentScore")
    console.log(this.testScore)
    this.testTitle = sessionStorage.getItem("currentTest")
  }

  onClick()
  {
    this._router.navigate(['/loginsuccess'])
  }

}
