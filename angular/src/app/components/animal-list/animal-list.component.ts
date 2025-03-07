import { Component, OnInit, inject } from '@angular/core';
import { AnimalService } from '../../services/animal.service';
import { Animal } from '../../models/zoo.model';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-animal-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css'],
})
export class AnimalListComponent implements OnInit {
  animals: Animal[] = [];
  displayedColumns: string[] = ['name', 'gender', 'age', 'aviary', 'species', 'foodSet', 'employees', 'actions'];

  private animalService = inject(AnimalService);
  private router = inject(Router);

  ngOnInit(): void {
    this.loadAnimals();
  }

  loadAnimals(): void {
    this.animalService.getAnimals().subscribe((data) => (this.animals = data));
  }

  editAnimal(id: number) {
    this.router.navigate([`/animal/${id}`]);
  }

  deleteAnimal(id: number) {
    this.animalService.deleteAnimal(id).subscribe(() => {
      this.animals = this.animals.filter((animal) => animal.id !== id);
    });
  }

  addAnimal() {
    this.router.navigate(['/animal/new']);
  }

  goBack(): void {
    this.router.navigate(['']);
  }
}
