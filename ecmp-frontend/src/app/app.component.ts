import { Component } from '@angular/core';
import {Router, RouterOutlet, Event, NavigationEnd } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NavbarComponent} from "./core/components/navbar/navbar.component";
import {BottombarComponent} from "./core/components/bottombar/bottombar.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NgbModule, RouterOutlet, NavbarComponent, BottombarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ecmp-frontend';
  constructor(private router: Router) {
    this.router.events.subscribe((event: Event) => {
      if (event instanceof NavigationEnd) {
        window.scrollTo(0, 0);
      }
    });
  }
}
