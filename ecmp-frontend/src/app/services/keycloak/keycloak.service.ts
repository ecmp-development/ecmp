import { Injectable } from '@angular/core';
import Keycloak from "keycloak-js";
import {UserProfile} from "./user-profile";

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  private _keycloak: Keycloak | undefined;
  private _profile: UserProfile | undefined;

  get keycloak() {
    if(!this._keycloak) {
      this._keycloak = new Keycloak({
        url: 'http://localhost:8081',
        realm: 'ECMP-API-DEV',
        clientId: 'ecmp_client'
      });
    }
    return this._keycloak;
  }

  get profile(): UserProfile | undefined {
    return this._profile;
  }

  constructor() { }

  async init() {

  }

  login() {
    return this.keycloak?.login({
      redirectUri: 'http://localhost:4200/login',
    });
  }

  logout() {
    return this.keycloak?.logout({
      redirectUri: 'http://localhost:4200/login',
    });
  }
}
