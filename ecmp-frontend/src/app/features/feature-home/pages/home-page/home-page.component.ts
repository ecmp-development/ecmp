import { Component } from '@angular/core';
import {NgOptimizedImage} from "@angular/common";
import {Router} from "@angular/router";
import {HomeVideoComponent} from "../../components/home-video/home-video.component";
import {HomeBenefitsComponent} from "../../components/home-benefits/home-benefits.component";
import {HomeBetaComponent} from "../../components/home-beta/home-beta.component";

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
export class HomePageComponent {

}
