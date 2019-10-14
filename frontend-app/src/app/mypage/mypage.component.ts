import { Component, OnInit } from '@angular/core';
import { User } from "../model/user";
import { ActivatedRoute } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import { Apollo } from "apollo-angular";
import { MypageGQL, MypageQuery } from "../graphql";
import { map, take } from "rxjs/operators";

@Component({
  selector: 'app-mypage',
  templateUrl: './mypage.component.html',
  styleUrls: ['./mypage.component.scss']
})
export class MypageComponent implements OnInit {
  user: User = new User("", ""); // TODO: lazy loading

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private apollo: Apollo,
    private gql: MypageGQL
  ) {
  }

  ngOnInit() {
    this.getProfileByGraphql()
      .subscribe(
        (r: MypageQuery) => this.user = new User(r.me.id, r.me.mailAddress),
        error => console.log(error)
      );
  }

  private getProfileByGraphql() {
    return this.apollo.watchQuery({
      query: this.gql.document,
    }).valueChanges.pipe(
      map(res => res.data),
      take(1)
    )
  }

}
