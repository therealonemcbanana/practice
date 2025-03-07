import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Species } from '../models/zoo.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SpeciesService {
  private apiUrl = `${environment.baseServerUrl}/species`;

  private http = inject(HttpClient);

  getSpecies(): Observable<Species[]> {
    return this.http.get<Species[]>(this.apiUrl);
  }

  getSpeciesById(id: number): Observable<Species> {
    return this.http.get<Species>(`${this.apiUrl}/${id}`);
  }

  addSpecies(species: Species): Observable<Species> {
    return this.http.post<Species>(this.apiUrl, species);
  }

  updateSpecies(id: number, species: Species): Observable<Species> {
    return this.http.put<Species>(`${this.apiUrl}/${id}`, species);
  }

  deleteSpecies(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
