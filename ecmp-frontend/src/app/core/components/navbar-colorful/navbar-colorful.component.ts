import { Component } from '@angular/core';
import {NgIf} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar-colorful',
  standalone: true,
    imports: [
        NgIf
    ],
  templateUrl: './navbar-colorful.component.html',
  styleUrl: './navbar-colorful.component.css'
})
export class NavbarColorfulComponent {
  constructor(private router: Router) {}

  navigateTo(site: string){
    this.router.navigate(['/' + site]);
  }
}
