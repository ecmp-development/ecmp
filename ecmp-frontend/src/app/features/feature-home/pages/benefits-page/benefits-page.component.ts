import { Component } from '@angular/core';
import {NavbarColorfulComponent} from "../../../../core/components/navbar-colorful/navbar-colorful.component";
import {BenefitsHeaderComponent} from "../../components/benefits-header/benefits-header.component";
import {BenefitsBodyComponent} from "../../components/benefits-body/benefits-body.component";

@Component({
  selector: 'app-benefits-page',
  standalone: true,
  imports: [
    NavbarColorfulComponent,
    BenefitsHeaderComponent,
    BenefitsBodyComponent
  ],
  templateUrl: './benefits-page.component.html',
  styleUrl: './benefits-page.component.css'
})
export class BenefitsPageComponent {

}
