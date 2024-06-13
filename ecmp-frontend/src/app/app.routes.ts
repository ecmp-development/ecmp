import { Routes } from '@angular/router';
import {FeatureHomeComponent} from "./features/feature-home/feature-home.component";
import {FeatureProductsComponent} from "./features/feature-products/feature-products.component";

export const routes: Routes = [
  {
    path: '',
    component: FeatureHomeComponent
  },
  {
    path: 'products',
    component: FeatureProductsComponent
  }
];
