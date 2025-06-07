import { TestBed } from '@angular/core/testing';

import { ClearToCloseService } from './clear-to-close-service';

describe('ClearToCloseService', () => {
  let service: ClearToCloseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClearToCloseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
