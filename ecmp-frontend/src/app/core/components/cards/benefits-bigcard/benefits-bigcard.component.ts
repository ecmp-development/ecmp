import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-benefits-bigcard',
  standalone: true,
  imports: [],
  templateUrl: './benefits-bigcard.component.html',
  styleUrl: './benefits-bigcard.component.css'
})
export class BenefitsBigcardComponent {
  @Input() imageUrl!: string;
  @Input() title!: string;
  @Input() description!: string;
}
