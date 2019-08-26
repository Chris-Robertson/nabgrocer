import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetItemByIdComponent } from './get-item-by-id.component';

describe('GetItemByIdComponent', () => {
  let component: GetItemByIdComponent;
  let fixture: ComponentFixture<GetItemByIdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetItemByIdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetItemByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
