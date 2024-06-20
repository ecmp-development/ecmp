import { Component } from '@angular/core';
import {BenefitCardComponent} from "../../../../core/components/cards/benefit-card/benefit-card.component";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-home-beta',
  standalone: true,
    imports: [
        BenefitCardComponent,
        NgForOf
    ],
  templateUrl: './home-beta.component.html',
  styleUrl: './home-beta.component.css'
})
export class HomeBetaComponent {

}
