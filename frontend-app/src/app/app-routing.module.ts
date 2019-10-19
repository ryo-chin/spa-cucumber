import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from "./signup/signup.component";
import { UserProfileComponent } from "./user-profile/user-profile.component";
import { MypageComponent } from "./mypage/mypage.component";
import { LoginComponent } from "./login/login.component";
import { LoginCompleteComponent } from "./login/complete/login-complete.component";

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'login/complete', component: LoginCompleteComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'user/:id', component: UserProfileComponent},
  {path: 'mypage', component: MypageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
