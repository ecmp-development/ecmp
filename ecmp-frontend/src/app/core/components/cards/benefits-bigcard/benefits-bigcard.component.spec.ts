import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BenefitsBigcardComponent } from './benefits-bigcard.component';

describe('BenefitsBigcardComponent', () => {
  let component: BenefitsBigcardComponent;
  let fixture: ComponentFixture<BenefitsBigcardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BenefitsBigcardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BenefitsBigcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
