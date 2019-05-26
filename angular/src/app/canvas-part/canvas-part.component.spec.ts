import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CanvasPartComponent } from './canvas-part.component';

describe('CanvasPartComponent', () => {
  let component: CanvasPartComponent;
  let fixture: ComponentFixture<CanvasPartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CanvasPartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CanvasPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
