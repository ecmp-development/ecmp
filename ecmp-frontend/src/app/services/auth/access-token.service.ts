import { Injectable } from '@angular/core';
import {requestConfig} from "../../app.config";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccessTokenService {

  constructor() { }


  getTokenLogin(username: string, password: string) {
    fetch(requestConfig.auth.kc_auth_token, {
      method: 'POST',
      headers: {
        "Content-Type": 'application/x-www-form-urlencoded;charset=UTF-8',
      },
      body: new URLSearchParams({
        'grant_type': requestConfig.auth.kc_auth_grant_type,
        'client_id': requestConfig.auth.kc_auth_client_id,
        'username': username,
        'password': password
      })
    }).then(res => {
      if (res.ok) return res.json();
      else return { refresh_token: 'error' };
    })
      .then(data => {
        if (data.access_token === 'error') {
          console.log("Error in parsing Token")
        } else {
          localStorage.setItem('refresh_token', data.refresh_token);
        }
      });
  }
}
