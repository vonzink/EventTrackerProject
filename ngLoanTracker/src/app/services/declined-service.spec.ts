import { TestBed } from '@angular/core/testing';

import { DeclinedService } from './declined-service';

describe('DeclinedService', () => {
  let service: DeclinedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeclinedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
