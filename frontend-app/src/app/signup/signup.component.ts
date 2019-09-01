import { Component, OnInit } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  userInputForm;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient
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
    this.http.post("http://localhost:8080/api/users", userInput, httpOptions)
      .subscribe(
        () => alert("success"),
        error => {
          console.log(error);
          alert("fail")
        })
  }
}
