import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AnimalService } from '../../services/animal.service';
import { AviaryService } from '../../services/aviary.service';
import { SpeciesService } from '../../services/species.service';
import { EmployeeService } from '../../services/employee.service';
import { FoodService } from '../../services/food.service';
import { Animal, Aviary, Employee, Food, Species } from '../../models/zoo.model';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-animal-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatInputModule, MatSelectModule, MatButtonModule, MatCardModule],
  templateUrl: './animal-form.component.html',
  styleUrls: ['./animal-form.component.css'],
})
export class AnimalFormComponent implements OnInit {
  animalForm: FormGroup;
  isEditMode = false;
  animalId: number | null = null;
  aviaries: Aviary[] = [];
  species: Species[] = [];
  foods: Food[] = [];
  employees: Employee[] = [];

  constructor(
    private fb: FormBuilder,
    private animalService: AnimalService,
    private aviaryService: AviaryService,
    private speciesService: SpeciesService,
    private foodService: FoodService,
    private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.animalForm = this.fb.group({
      name: ['', Validators.required],
      gender: ['', Validators.required],
      age: ['', [Validators.required, Validators.min(0)]],
      aviary: [null, Validators.required],
      species: [null, Validators.required],
      foodSet: [[]],
      employees: [[]],
    });
  }

  ngOnInit(): void {
    this.animalId = +this.route.snapshot.paramMap.get('id')!;

    this.aviaryService.getAviaries().subscribe((data) => (this.aviaries = data));
    this.speciesService.getSpecies().subscribe((data) => (this.species = data));
    this.foodService.getFoods().subscribe((data) => (this.foods = data));
    this.employeeService.getEmployees().subscribe((data) => (this.employees = data));

    if (this.animalId) {
      this.isEditMode = true;
      this.loadAnimalForEdit();
    }
  }

  loadAnimalForEdit(): void {
    this.animalService.getAnimalById(this.animalId!).subscribe((animal) => {
      this.animalForm.patchValue({
        name: animal.name,
        gender: animal.gender,
        age: animal.age,
        aviary: animal.aviary,
        species: animal.species,
        foodSet: animal.foodSet,
        employees: animal.employees,
      });
      
      console.log(this.animalForm.value);
    });
  }

  onSubmit(): void {
    if (this.animalForm.valid) {
      const animalData = this.animalForm.value;
      console.log(animalData);

      if (this.isEditMode) {
        this.animalService.updateAnimal(this.animalId!, animalData).subscribe(() => {
          this.router.navigate(['/animal']);
        });
      } else {
        this.animalService.addAnimal(animalData).subscribe(() => {
          this.router.navigate(['/animal']);
        });
      }
    }
  }

  goBack(): void {
    this.router.navigate(['/animal']);
  }
}
