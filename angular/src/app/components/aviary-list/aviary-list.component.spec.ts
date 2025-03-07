import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AviaryListComponent } from './aviary-list.component';

describe('AviaryListComponent', () => {
  let component: AviaryListComponent;
  let fixture: ComponentFixture<AviaryListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AviaryListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AviaryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
