import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarColorfulComponent } from './navbar-colorful.component';

describe('NavbarColorfulComponent', () => {
  let component: NavbarColorfulComponent;
  let fixture: ComponentFixture<NavbarColorfulComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarColorfulComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NavbarColorfulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
