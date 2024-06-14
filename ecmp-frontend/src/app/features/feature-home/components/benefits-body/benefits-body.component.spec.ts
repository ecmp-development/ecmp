import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BenefitsBodyComponent } from './benefits-body.component';

describe('BenefitsBodyComponent', () => {
  let component: BenefitsBodyComponent;
  let fixture: ComponentFixture<BenefitsBodyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BenefitsBodyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BenefitsBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
