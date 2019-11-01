import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoursesCatalogComponent } from './courses-catalog/courses-catalog.component';
import { FormsModule } from "@angular/forms";
import { AddNewCourseComponent } from './add-new-course/add-new-course.component';

@NgModule({
  declarations: [
    AppComponent,
    CoursesCatalogComponent,
    AddNewCourseComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
