import { TestBed } from '@angular/core/testing';

import { RestActividadService } from './rest-actividad.service';

describe('RestActividadService', () => {
  let service: RestActividadService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestActividadService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
