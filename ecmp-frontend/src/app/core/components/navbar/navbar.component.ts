import {Component, HostListener} from '@angular/core';
import {NgClass, NgIf, NgOptimizedImage} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NgClass,
    NgOptimizedImage,
    NgIf
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  isScrolled = false;
  isHovered = false;

  constructor(private router: Router) {}

  navigateTo(site: string){
    this.router.navigate(['/' + site]);
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.pageYOffset > 0;
  }

  onHover(state: boolean) {
    this.isHovered = state;
  }
}
