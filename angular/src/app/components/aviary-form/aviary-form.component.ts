import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AviaryService } from '../../services/aviary.service';

@Component({
  selector: 'app-aviary-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './aviary-form.component.html',
  styleUrls: ['./aviary-form.component.css'],
})
export class AviaryFormComponent implements OnInit {
  aviaryForm: FormGroup;
  aviaryId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private aviaryService: AviaryService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.aviaryForm = this.fb.group({
      size: ['', [Validators.required, Validators.min(1)]],
      state: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.aviaryId = +this.route.snapshot.paramMap.get('id')!;

    if (this.aviaryId) {
      this.aviaryService.getAviaryById(this.aviaryId).subscribe((aviary) => {
        this.aviaryForm.patchValue(aviary);
      });
    }
  }

  onSubmit(): void {
    if (this.aviaryForm.valid) {
      if (this.aviaryId) {
        this.aviaryService.updateAviary(this.aviaryId, this.aviaryForm.value).subscribe(() => {
          this.router.navigate(['/aviary']);
        });
      } else {
        this.aviaryService.addAviary(this.aviaryForm.value).subscribe(() => {
          this.router.navigate(['/aviary']);
        });
      }
    }
  }

  goBack(): void {
    this.router.navigate(['/aviary']);
  }
}
