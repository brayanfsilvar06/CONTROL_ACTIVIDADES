import { TestBed } from '@angular/core/testing';

import { RestPersonaService } from './rest-persona.service';

describe('RestPersonaService', () => {
  let service: RestPersonaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestPersonaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
