import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {HomePageComponent} from "./pages/home-page/home-page.component";
import {NavbarComponent} from "../../core/components/navbar/navbar.component";
import {BottombarComponent} from "../../core/components/bottombar/bottombar.component";

@Component({
  selector: 'app-feature-home',
  standalone: true,
    imports: [
        RouterOutlet,
        HomePageComponent,
        NavbarComponent,
        BottombarComponent
    ],
  templateUrl: './feature-home.component.html',
  styleUrl: './feature-home.component.css'
})
export class FeatureHomeComponent {

}
