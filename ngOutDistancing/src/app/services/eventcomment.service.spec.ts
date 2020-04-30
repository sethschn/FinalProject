import { TestBed } from '@angular/core/testing';

import { EventcommentService } from './eventcomment.service';

describe('EventcommentService', () => {
  let service: EventcommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventcommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
