import { Injectable } from '@angular/core';
import { WebAuth } from 'auth0-js';
import * as auth0 from 'auth0-js';
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // auth0Client: WebAuth;
  accessToken;
  idToken;
  expiresAt;

  auth0Client: WebAuth = new auth0.WebAuth({
    domain: environment.auth0.domain,
    clientID: environment.auth0.clientID,
    redirectUri: environment.auth0.redirectUri,
    responseType: environment.auth0.responseType,
    // responseMode: 'query'
  });

  constructor() {
    this.login = this.login.bind(this);
    // this.logout = this.logout.bind(this);
    this.handleAuthentication = this.handleAuthentication.bind(this);
    this.isAuthenticated = this.isAuthenticated.bind(this);
    this.getAccessToken = this.getAccessToken.bind(this);
    this.getIdToken = this.getIdToken.bind(this);
    // this.renewSession = this.renewSession.bind(this);
  }

  login(mail, password, errCallback) {
    this.auth0Client.login({
      realm: "Username-Password-Authentication",
      username: mail,
      password: password,
    }, (err, authResult) => {
      console.log(authResult);
      errCallback(err);
    })
  }

  handleAuthentication(callback, errorCallback) {
    this.auth0Client.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        this.setSession(authResult);
        callback(authResult);
        return;
      } else if (err) {
        console.log(err);
        alert(`Error: ${err.error}. Check the console for further details.`);
        errorCallback(err);
        return;
      }
      if (this.isAuthenticated()) {
        callback();
        return;
      }
      errorCallback();
      return;
    });
  }

  getAccessToken() {
    return this.accessToken;
  }

  getIdToken() {
    return this.idToken;
  }

  setSession(authResult) {
    // Set isLoggedIn flag in localStorage
    localStorage.setItem('isLoggedIn', 'true');
    localStorage.setItem('token', authResult.idToken);

    // Set the time that the access token will expire at
    let expiresAt = (authResult.expiresIn * 1000) + new Date().getTime();
    this.accessToken = authResult.accessToken;
    this.idToken = authResult.idToken;
    this.expiresAt = expiresAt;
  }

  isAuthenticated() {
    // Check whether the current time is past the
    // access token's expiry time
    let expiresAt = this.expiresAt;
    return new Date().getTime() < expiresAt;
  }
}
