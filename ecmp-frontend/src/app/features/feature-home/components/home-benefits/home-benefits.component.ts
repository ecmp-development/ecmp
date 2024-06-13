import { Component } from '@angular/core';
import {BenefitCardComponent} from "./benefit-card/benefit-card.component";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-home-benefits',
  standalone: true,
  imports: [
    BenefitCardComponent,
    NgForOf
  ],
  templateUrl: './home-benefits.component.html',
  styleUrl: './home-benefits.component.css'
})
export class HomeBenefitsComponent {
  cardsData = [
    { backgroundUrl: 'assets/backgrounds/useability-bg.png', title: '⚡️ Useability', description: 'We combine the technology for your needs.'},
    { backgroundUrl: 'assets/backgrounds/computer-security.png', title: '🔒 Security', description: 'Your privacy and security is one of our most important topic.'},
    { backgroundUrl: 'assets/backgrounds/support-worker.png', title: '💙 Support', description: 'You have questions or problems with our services? Our team is there for you.'}
  ]
}
