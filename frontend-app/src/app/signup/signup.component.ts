import { Component, OnInit } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";
import { User } from "../model/user";
import { environment } from "../../environments/environment";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  userInputForm;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.userInputForm = formBuilder.group({
      mailAddress: '',
      password: ''
    })
  }

  ngOnInit() {
  }

  onSubmit(userInput) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    this.http.post<User>(environment.apiUrl + "/api/users", userInput, httpOptions)
      .subscribe(
        user => this.router.navigateByUrl("/user/" + user.id),
        error => {
          console.log(error);
          alert("fail")
        })
  }
}
