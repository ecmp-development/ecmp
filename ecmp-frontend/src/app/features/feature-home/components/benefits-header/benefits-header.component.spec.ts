import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BenefitsHeaderComponent } from './benefits-header.component';

describe('BenefitsHeaderComponent', () => {
  let component: BenefitsHeaderComponent;
  let fixture: ComponentFixture<BenefitsHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BenefitsHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BenefitsHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
