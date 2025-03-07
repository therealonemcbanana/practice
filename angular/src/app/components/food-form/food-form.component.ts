import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { FoodService } from '../../services/food.service';  // Путь к вашему сервису
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-food-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],  // только необходимые модули
  templateUrl: './food-form.component.html',
  styleUrls: ['./food-form.component.css'],
})
export class FoodFormComponent {
  foodForm: FormGroup;
  foodId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private foodService: FoodService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.foodForm = this.fb.group({
      name: ['', [Validators.required]],
      amount: ['', [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit(): void {
    this.foodId = +this.route.snapshot.paramMap.get('id')!;

    if (this.foodId) {
      this.foodService.getFoodById(this.foodId).subscribe((food) => {
        this.foodForm.patchValue(food);
      });
    }
  }

  onSubmit(): void {
    if (this.foodForm.valid) {
      if (this.foodId) {
        this.foodService.updateFood(this.foodId, this.foodForm.value).subscribe(() => {
          this.router.navigate(['/food']);
        });
      } else {
        this.foodService.addFood(this.foodForm.value).subscribe(() => {
          this.router.navigate(['/food']);
        });
      }
    }
  }

  goBack(): void {
    this.router.navigate(['/food']);
  }
}
