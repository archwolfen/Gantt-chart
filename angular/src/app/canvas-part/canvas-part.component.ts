import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Activity} from '../activity';

declare function drawChart(): any;

@Component({
  selector: 'app-canvas-part',
  templateUrl: './canvas-part.component.html',
  styleUrls: ['./canvas-part.component.css']
})
export class CanvasPartComponent implements OnInit {
  @Input() activities: Array<Activity>;

  constructor() {

  }


  ngOnInit() {
    drawChart();
  }
}
