import { Component, OnInit } from '@angular/core';
import { AuthService } from "../shared/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  mail: string;
  password: string;
  errorMessage: string;

  constructor(private auth: AuthService) {
  }

  ngOnInit() {
  }

  login() {
    this.auth.login(this.mail, this.password, (err) => {
      if (err.code === 'access_denied') {
        this.errorMessage = 'Incorrect email address or password';
        return;
      }
      this.errorMessage = 'login error';
    });
  }
}
