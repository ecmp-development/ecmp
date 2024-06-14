import {Component, Input} from '@angular/core';
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-benefit-card',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './benefit-card.component.html',
  styleUrl: './benefit-card.component.css'
})
export class BenefitCardComponent {
  @Input() backgroundUrl!: string;
  @Input() title!: string;
  @Input() description!: string;
}
