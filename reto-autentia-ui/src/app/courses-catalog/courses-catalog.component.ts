import { Component, OnInit } from '@angular/core';
import { Course } from "../course";
import { HttpClient } from '@angular/common/http';
import { DataService } from "../data.service";
import { COURSES } from "../mock-courses";

@Component({
  selector: 'app-courses-catalog',
  templateUrl: './courses-catalog.component.html',
  styleUrls: ['./courses-catalog.component.css']
})
export class CoursesCatalogComponent implements OnInit {

  title = 'Catalogo de cursos';

  items = COURSES;

  nameOfCourseTHNoOrder = 'Título';
  nameOfCourseTHOrderedAsc = 'Título ▲';
  nameOfCourseTHOrderedDesc = 'Título ▼';

  constructor(private httpClient: HttpClient, private dataService: DataService) { }

  ngOnInit() {
    this.dataService.getActiveCourses(this.dataService.orderByName);
  }

  onClickName(): void {
    if (this.dataService.orderByName) {
      this.dataService.courses.sort((a,b) => (a.name < b.name) ? 1 : -1);
    }
    else {
      this.dataService.courses.sort((a,b) => (a.name > b.name) ? 1 : -1);
    }
    this.dataService.orderByName = !this.dataService.orderByName;
    // this.dataService.getActiveCourses(this.dataService.orderByName);
  }

  onAddCourse(): void {
    this.dataService.showAddCourse = true;
  }

  translateLevel(level): string {
    if(level == 'BASIC') return 'Principiante';
    if(level == 'MEDIUM') return 'Intermedio';
    if(level == 'ADVANCE') return 'Avanzado';
  }
}
