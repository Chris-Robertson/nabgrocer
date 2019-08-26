import { TestBed } from '@angular/core/testing';

import { NabGrocerApiService } from './nab-grocer-api.service';

describe('NabGrocerApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NabGrocerApiService = TestBed.get(NabGrocerApiService);
    expect(service).toBeTruthy();
  });
});
