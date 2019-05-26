import {Component, Input, OnInit} from '@angular/core';
import {Activity} from '../activity';

@Component({
  selector: 'app-list-activities',
  templateUrl: './list-activities.component.html',
  styleUrls: ['./list-activities.component.css']
})
export class ListActivitiesComponent implements OnInit {
  @Input() activities: Array<Activity>;

  constructor() { }

  ngOnInit() {
  }

}
