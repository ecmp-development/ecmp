import { Component } from '@angular/core';
import {BenefitsBigcardComponent} from "../../../../core/components/cards/benefits-bigcard/benefits-bigcard.component";
import {BenefitCardComponent} from "../../../../core/components/cards/benefit-card/benefit-card.component";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-benefits-body',
  standalone: true,
  imports: [
    BenefitsBigcardComponent,
    BenefitCardComponent,
    NgForOf
  ],
  templateUrl: './benefits-body.component.html',
  styleUrl: './benefits-body.component.css'
})
export class BenefitsBodyComponent {
  cardsData = [
    { imageUrl: 'assets/backgrounds/useability-bg.png', title: 'Usability', description: 'we understand that usability is crucial for our customers. Our commitment to creating intuitive and user-friendly products ensures that you have a seamless and enjoyable experience. Usability is an ongoing priority for us. We continually gather feedback and analyze user behavior to identify areas for improvement, ensuring that our products evolve to meet your changing needs.'},
    { imageUrl: 'assets/backgrounds/computer-security.png', title: 'Security', description: 'At ecmp, we understand that security and data protection are paramount for our customers. In today’s digital age, safeguarding your sensitive information is not just an obligation, but a commitment we take seriously. We employ cutting-edge security technologies to protect your data against unauthorized access, breaches, and other cyber threats. Our multi-layered security framework ensures that your information remains confidential and secure.'},
    { imageUrl: 'assets/backgrounds/support-worker.png', title: 'Support', description: 'Our customer support team is available around the clock to assist you. Whether you have a question, need assistance, or encounter an issue, we are here to help you anytime, anywhere. We value your time and strive to provide prompt responses to all inquiries. Our goal is to resolve your issues quickly and efficiently, minimizing any disruption to your experience.'}
  ]
}
