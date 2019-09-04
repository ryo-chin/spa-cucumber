import { Component, OnInit } from '@angular/core';
import { User } from "../model/user";
import { ActivatedRoute } from "@angular/router";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  user: User;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient
  ) { }

  ngOnInit() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    const id = this.route.snapshot.paramMap.get('id');
    this.http.get<User>(environment.apiUrl + "/api/users/" + id, httpOptions)
      .subscribe(
        user => this.user = user,
        error => alert("fail")
      )
  }
}
