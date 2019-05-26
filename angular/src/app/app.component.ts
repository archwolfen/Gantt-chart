import { Component } from '@angular/core';
import {Activity} from './activity';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'GanttChart';
  private activities = [
    new Activity('Client meeting', 0, '25 Apr', '28 Apr', 90, [1], 4),
    new Activity('Research', 1, '1 May', '2 May', 99, [2], 2),
    new Activity('Written plan', 2, '3 May', '4 May', 100, [3, 4, 5], 2),
    new Activity('Find collaborators', 3, '5 May', '9 May', 100, [6], 5),
    new Activity('Write content', 6, '10 May', '12 May', 100, null, 3),
    new Activity('Wireframing', 4, '5 May', '12 May', 100, [7], 8),
    new Activity('Design', 7, '15 May', '16 May', 100, null, 2),
    new Activity('Choose technology', 5, '5 May', '12 May', 100, [8], 8),
    new Activity('Architecture', 8, '15 May', '19 May', 100, null, 5)
  ];
}
