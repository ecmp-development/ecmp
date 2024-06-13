import { Component } from '@angular/core';
import {NavbarColorfulComponent} from "../../core/components/navbar-colorful/navbar-colorful.component";

@Component({
  selector: 'app-feature-products',
  standalone: true,
  imports: [
    NavbarColorfulComponent
  ],
  templateUrl: './feature-products.component.html',
  styleUrl: './feature-products.component.css'
})
export class FeatureProductsComponent {

}
