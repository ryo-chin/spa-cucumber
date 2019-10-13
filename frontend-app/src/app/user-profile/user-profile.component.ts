import { Component, OnInit } from '@angular/core';
import { User } from "../model/user";
import { ActivatedRoute } from "@angular/router";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Apollo } from "apollo-angular";
import { UsersGQL, UsersQuery } from "../graphql";
import { map, take } from "rxjs/operators";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  user: User;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private apollo: Apollo,
    private gql: UsersGQL
  ) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.getProfileByGraphql(id)
      .subscribe(
        (r: UsersQuery) => this.user = new User(r.user.id, r.user.mailAddress),
        error => console.log(error)
      );
  }

  private getProfileByGraphql(id) {
    return this.apollo.watchQuery({
      query: this.gql.document,
      variables: {id: id}
    }).valueChanges.pipe(
      map(res => res.data),
      take(1)
    )
  }

  private getProfileByRestApi(id) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    this.http.get<User>(environment.apiUrl + "/api/users/" + id, httpOptions)
      .subscribe(
        user => this.user = user,
        error => alert("fail")
      )
  }
}
