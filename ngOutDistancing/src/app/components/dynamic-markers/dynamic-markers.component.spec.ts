import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DynamicMarkersComponent } from './dynamic-markers.component';

describe('DynamicMarkersComponent', () => {
  let component: DynamicMarkersComponent;
  let fixture: ComponentFixture<DynamicMarkersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DynamicMarkersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DynamicMarkersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
