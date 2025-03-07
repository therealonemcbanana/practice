import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { SpeciesService } from '../../services/species.service';
import { HttpClientModule } from '@angular/common/http';
import { Species } from '../../models/zoo.model';

@Component({
  standalone: true,
  selector: 'app-species-list',
  imports: [CommonModule, RouterModule, HttpClientModule],
  templateUrl: './species-list.component.html',
  styleUrls: ['./species-list.component.css'],
})
export class SpeciesListComponent implements OnInit {
  speciesList: Species[] = [];
  private speciesService = inject(SpeciesService);
  private router = inject(Router);

  ngOnInit() {
    this.loadSpecies();
  }

  loadSpecies() {
    this.speciesService.getSpecies().subscribe((data) => (this.speciesList = data));
  }

  addSpecies() {
    this.router.navigate(['/species/new']);
  }

  editSpecies(id: number) {
    this.router.navigate([`/species/${id}`]);
  }

  deleteSpecies(id: number) {
    this.speciesService.deleteSpecies(id).subscribe(() => {
      this.speciesList = this.speciesList.filter((species) => species.id !== id);
    });
  }

  goBack(): void {
    this.router.navigate(['']);
  }
}
