import {Injectable} from '@angular/core';
import {requestConfig} from "../../app.config";

@Injectable({
  providedIn: 'root'
})
export class AccessTokenService {

  constructor() {
  }

  async getTokenLogin(username: string, password: string): Promise<string> {
    return await fetch(requestConfig.auth.kc_auth_token, {
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
      else return 'error';
    })
      .then(data => {
        if (data === 'error') {
          return "Error in parsing Token"
        } else {
          return data.access_token;
        }
      })
      .catch(e => {
        console.log("Failed to get Token " + e)
        return e;
      });
  }
}
