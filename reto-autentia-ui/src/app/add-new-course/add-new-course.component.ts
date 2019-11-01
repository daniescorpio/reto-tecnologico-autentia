import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { Course } from '../course'
import { HttpClient } from '@angular/common/http';
import { DataService } from "../data.service";

@Component({
  selector: 'app-add-new-course',
  templateUrl: './add-new-course.component.html',
  styleUrls: ['./add-new-course.component.css']
})
export class AddNewCourseComponent implements OnInit {

  show = this.dataService.showAddCourse;

  url = '/api';

  constructor(private httpClient: HttpClient, private dataService: DataService) { }

  ngOnInit() {}

  onAddCourse(): void {
    if (this.dataService.courseToAdd.level == 'Principiante') this.dataService.courseToAdd.level = 'BASIC';
    if (this.dataService.courseToAdd.level == 'Intermedio') this.dataService.courseToAdd.level = 'MEDIUM';
    if (this.dataService.courseToAdd.level == 'Avanzado') this.dataService.courseToAdd.level = 'ADVANCE';
    this.dataService.postNewCourse();
    this.dataService.showAddCourse = false;
    this.dataService.courseToAdd = {
      id: null,
      isActive: null,
      name: null,
      level: null,
      hours: null,
      teacher: null
    }
  }
}
