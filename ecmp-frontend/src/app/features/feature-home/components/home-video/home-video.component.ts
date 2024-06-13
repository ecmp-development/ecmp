import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home-video',
  standalone: true,
  imports: [],
  templateUrl: './home-video.component.html',
  styleUrl: './home-video.component.css'
})
export class HomeVideoComponent {
  constructor(private router: Router) {}

  navigateToProducts() {
    this.router.navigate(['/products']);
  }
}

