import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FoodService } from '../../services/food.service';
import { HttpClientModule } from '@angular/common/http';
import { Food } from '../../models/zoo.model';

@Component({
  standalone: true,
  selector: 'app-food-list',
  imports: [CommonModule, RouterModule, HttpClientModule],
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css'],
})
export class FoodListComponent implements OnInit {
  foods: Food[] = [];
  private foodService = inject(FoodService);
  private router = inject(Router);

  ngOnInit() {
    this.loadFoods();
  }

  loadFoods() {
    this.foodService.getFoods().subscribe((data) => (this.foods = data));
  }

  addFood() {
    this.router.navigate(['/food/new']);
  }

  editFood(id: number) {
    this.router.navigate([`/food/${id}`]);
  }

  deleteFood(id: number) {
    this.foodService.deleteFood(id).subscribe(() => {
      this.foods = this.foods.filter((food) => food.id !== id);
    });
  }

  goBack(): void {
    this.router.navigate(['']);
  }
}
