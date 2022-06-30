import { TestBed } from '@angular/core/testing';

import { RestAuthapiService } from './rest-authapi.service';

describe('RestAuthapiService', () => {
  let service: RestAuthapiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestAuthapiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
