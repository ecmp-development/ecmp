import {Component, OnInit} from '@angular/core';
import {NgOptimizedImage} from "@angular/common";
import {HomeVideoComponent} from "../../components/home-video/home-video.component";
import {HomeBenefitsComponent} from "../../components/home-benefits/home-benefits.component";
import {HomeBetaComponent} from "../../components/home-beta/home-beta.component";
import {AccessTokenService} from "../../../../services/auth/access-token.service";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    NgOptimizedImage,
    HomeVideoComponent,
    HomeBenefitsComponent,
    HomeBetaComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit {

  loginService: AccessTokenService = new AccessTokenService();

  ngOnInit() {
    this.loginService.getTokenLogin("ecmpuser1", "123")
      .then(function (result) {
        localStorage.setItem("access_token", result)
      })
  }
}
