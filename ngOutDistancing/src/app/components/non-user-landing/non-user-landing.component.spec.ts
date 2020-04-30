import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NonUserLandingComponent } from './non-user-landing.component';

describe('NonUserLandingComponent', () => {
  let component: NonUserLandingComponent;
  let fixture: ComponentFixture<NonUserLandingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NonUserLandingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NonUserLandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
