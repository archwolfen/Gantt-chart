import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Activity} from '../activity';

@Component({
  selector: 'app-canvas-part',
  templateUrl: './canvas-part.component.html',
  styleUrls: ['./canvas-part.component.css']
})
export class CanvasPartComponent implements OnInit {
  @Input() activities: Array<Activity>;

  private context: CanvasRenderingContext2D;

  constructor() { }

  @ViewChild('myCanvas') myCanvas: ElementRef;

  ngOnInit() {
    this.context = this.myCanvas.nativeElement.getContext('2d');

    this.drawBar(5, 5, 200, 40, 40, this.activities[0].Title());
    this.drawBar(400, 50, 200, 40, 40, this.activities[1].Title());
    this.drawBar(600, 90, 200, 40, 40, this.activities[2].Title());

  }

  public drawBar(rectX: number, rectY: number, rectWidth: number, rectHeight: number, cornerRadius: number, title: string) {
    this.context.lineJoin = 'round';
    this.context.lineWidth = cornerRadius;
    this.context.fillStyle = '#90ee90';
    this.context.strokeStyle = '#90ee90';

    this.context.strokeRect(rectX + (cornerRadius / 2), rectY + (cornerRadius / 2), rectWidth - cornerRadius, rectHeight - cornerRadius);
    this.context.fillRect(rectX + (cornerRadius / 2), rectY + (cornerRadius / 2), rectWidth - cornerRadius, rectHeight - cornerRadius);
    this.context.font = '14px Poppins';
    this.context.fillStyle = 'black';
    this.context.fillText(title, rectX + 10, rectY + rectHeight + 15);
  }

}
