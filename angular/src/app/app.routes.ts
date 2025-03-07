import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component'; // Убедись, что путь правильный
import { FoodListComponent } from './components/food-list/food-list.component';
import { FoodFormComponent } from './components/food-form/food-form.component';
import { AviaryListComponent } from './components/aviary-list/aviary-list.component';
import { AviaryFormComponent } from './components/aviary-form/aviary-form.component';
import { SpeciesListComponent } from './components/species-list/species-list.component';
import { SpeciesFormComponent } from './components/species-form/species-form.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';
import { AnimalListComponent } from './components/animal-list/animal-list.component';
import { AnimalFormComponent } from './components/animal-form/animal-form.component';

export const appRoutes: Routes = [
  { path: '', component: HomeComponent },

  { path: 'food', component: FoodListComponent },
  { path: 'food/new', component: FoodFormComponent },
  { path: 'food/:id', component: FoodFormComponent },
  
  { path: 'aviary', component: AviaryListComponent },
  { path: 'aviary/new', component: AviaryFormComponent },
  { path: 'aviary/:id', component: AviaryFormComponent },  
  
  { path: 'species', component: SpeciesListComponent },
  { path: 'species/new', component: SpeciesFormComponent },
  { path: 'species/:id', component: SpeciesFormComponent },
  
  { path: 'employee', component: EmployeeListComponent },
  { path: 'employee/new', component: EmployeeFormComponent },
  { path: 'employee/:id', component: EmployeeFormComponent }, 

  
  { path: 'animal', component: AnimalListComponent },
  { path: 'animal/new', component: AnimalFormComponent },
  { path: 'animal/:id', component: AnimalFormComponent }, 
];
