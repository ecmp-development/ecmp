import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-benefit-card',
  standalone: true,
  imports: [],
  templateUrl: './benefit-card.component.html',
  styleUrl: './benefit-card.component.css'
})
export class BenefitCardComponent {
  @Input() backgroundUrl!: string;
  @Input() title!: string;
  @Input() description!: string;
}
