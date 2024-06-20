import {APP_INITIALIZER, ApplicationConfig} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {HttpClient} from "@angular/common/http";
import {KeycloakService} from "./services/keycloak/keycloak.service";

export function kcFactory(kcService: KeycloakService) {
  return () => kcService.init();
}

export const appConfig: ApplicationConfig = {
  providers: [
    HttpClient,
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: kcFactory,
      multi: true
    },
    provideRouter(routes)
  ]
};

export const requestConfig = {
  api: {
    host: 'http://localhost:8080/api/v1/'
  },
  auth: {
    kc_auth_grant_type: 'password',
    kc_auth_client_id: 'ecmp_client',
    kc_auth_token: 'http://localhost:8081/realms/ECMP-API-DEV/protocol/openid-connect/token',
  },
}
