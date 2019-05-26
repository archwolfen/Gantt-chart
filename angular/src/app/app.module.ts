import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { ListActivitiesComponent } from './list-activities/list-activities.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDividerModule, MatGridListModule, MatListModule} from '@angular/material';
import { CanvasPartComponent } from './canvas-part/canvas-part.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ListActivitiesComponent,
    CanvasPartComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatListModule,
    MatGridListModule,
    MatDividerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
