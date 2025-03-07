import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SpeciesService } from '../../services/species.service';

@Component({
  selector: 'app-species-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './species-form.component.html',
  styleUrls: ['./species-form.component.css'],
})
export class SpeciesFormComponent implements OnInit {
  speciesForm: FormGroup;
  speciesId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private speciesService: SpeciesService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.speciesForm = this.fb.group({
      title: ['', [Validators.required]],
      description: [''],
    });
  }

  ngOnInit(): void {
    this.speciesId = +this.route.snapshot.paramMap.get('id')!;

    if (this.speciesId) {
      this.speciesService.getSpeciesById(this.speciesId).subscribe((species) => {
        this.speciesForm.patchValue(species);
      });
    }
  }

  onSubmit(): void {
    if (this.speciesForm.valid) {
      if (this.speciesId) {
        this.speciesService.updateSpecies(this.speciesId, this.speciesForm.value).subscribe(() => {
          this.router.navigate(['/species']);
        });
      } else {
        this.speciesService.addSpecies(this.speciesForm.value).subscribe(() => {
          this.router.navigate(['/species']);
        });
      }
    }
  }

  goBack(): void {
    this.router.navigate(['/species']);
  }
}
