import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from "./course";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  courses: any = null;
  courseToAdd = new Course();
  orderByName = false;
  showAddCourse: boolean;

  constructor(private httpClient: HttpClient) { }

  getActiveCourses(order) {
    return this
      .httpClient
      .get(`/api/courses?order=${order}`)
      .subscribe(apiData => this.courses = apiData);
  }

  postNewCourse() {
    return this
      .httpClient
      .post(`/api/course`, this.courseToAdd)
      .subscribe(
        data  => {
          console.log("POST Request is successful ", data);
          this.getActiveCourses(this.orderByName);
        },
      );
  }
}
