import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  title = 'Zoo Management';

  constructor(private router: Router) {}

  navigateToAnimal() {
    this.router.navigate(['/animal']);
  }

  navigateToFood() {
    this.router.navigate(['/food']); 
  }

  navigateToAviary() {
    this.router.navigate(['/aviary']); 
  }

  navigateToEmployee() {
    this.router.navigate(['/employee']); 
  }

  navigateToSpecies() {
    this.router.navigate(['/species']); 
  }
}
