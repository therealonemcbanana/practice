import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AviaryFormComponent } from './aviary-form.component';

describe('AviaryFormComponent', () => {
  let component: AviaryFormComponent;
  let fixture: ComponentFixture<AviaryFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AviaryFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AviaryFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
