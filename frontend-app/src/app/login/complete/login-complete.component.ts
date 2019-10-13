import { Component, OnInit } from '@angular/core';
import { AuthService } from "../../shared/auth.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login-complete',
  templateUrl: './login-complete.component.html',
  styleUrls: ['./login-complete.component.scss']
})
export class LoginCompleteComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {
    this.auth.handleAuthentication((authResult) => {
      this.router.navigateByUrl('mypage');
    }, () => {
      this.router.navigateByUrl('login');
    });
  }
}
