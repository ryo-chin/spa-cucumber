import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { UserProfileComponent } from './user-profile/user-profile.component';
import { GraphQLModule } from "./graphql/graphal.module";
import { LoginCompleteComponent } from './login/complete/login-complete.component';
import { LoginComponent } from './login/login.component';
import { MypageComponent } from './mypage/mypage.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    UserProfileComponent,
    LoginCompleteComponent,
    LoginComponent,
    MypageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    GraphQLModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
