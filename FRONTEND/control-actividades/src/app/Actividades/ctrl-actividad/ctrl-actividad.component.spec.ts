import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CtrlActividadComponent } from './ctrl-actividad.component';

describe('CtrlActividadComponent', () => {
  let component: CtrlActividadComponent;
  let fixture: ComponentFixture<CtrlActividadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CtrlActividadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CtrlActividadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
