import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Food } from '../models/zoo.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class FoodService {
  private apiUrl = `${environment.baseServerUrl}/food`; 

  private http = inject(HttpClient);

  getFoods(): Observable<Food[]> {
    return this.http.get<Food[]>(this.apiUrl);
  }

  getFoodById(id: number): Observable<Food> {
    return this.http.get<Food>(`${this.apiUrl}/${id}`);
  }

  addFood(food: Food): Observable<Food> {
    return this.http.post<Food>(this.apiUrl, food);
  }

  updateFood(id: number, food: Food): Observable<Food> {
    return this.http.put<Food>(`${this.apiUrl}/${id}`, food);
  }

  deleteFood(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
