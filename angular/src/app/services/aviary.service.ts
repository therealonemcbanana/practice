import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Aviary } from '../models/zoo.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AviaryService {
  private apiUrl = `${environment.baseServerUrl}/aviary`;

  private http = inject(HttpClient);

  getAviaries(): Observable<Aviary[]> {
    return this.http.get<Aviary[]>(this.apiUrl);
  }

  getAviaryById(id: number): Observable<Aviary> {
    return this.http.get<Aviary>(`${this.apiUrl}/${id}`);
  }

  addAviary(aviary: Aviary): Observable<Aviary> {
    return this.http.post<Aviary>(this.apiUrl, aviary);
  }

  updateAviary(id: number, aviary: Aviary): Observable<Aviary> {
    return this.http.put<Aviary>(`${this.apiUrl}/${id}`, aviary);
  }

  deleteAviary(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
