import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { AviaryService } from '../../services/aviary.service';
import { HttpClientModule } from '@angular/common/http';
import { Aviary } from '../../models/zoo.model';

@Component({
  standalone: true,
  selector: 'app-aviary-list',
  imports: [CommonModule, RouterModule, HttpClientModule],
  templateUrl: './aviary-list.component.html',
  styleUrls: ['./aviary-list.component.css'],
})
export class AviaryListComponent implements OnInit {
  aviaries: Aviary[] = [];
  private aviaryService = inject(AviaryService);
  private router = inject(Router);

  ngOnInit() {
    this.loadAviaries();
  }

  loadAviaries() {
    this.aviaryService.getAviaries().subscribe((data) => (this.aviaries = data));
  }

  addAviary() {
    this.router.navigate(['/aviary/new']);
  }

  editAviary(id: number) {
    this.router.navigate([`/aviary/${id}`]);
  }

  deleteAviary(id: number) {
    this.aviaryService.deleteAviary(id).subscribe(() => {
      this.aviaries = this.aviaries.filter((aviary) => aviary.id !== id);
    });
  }

  goBack(): void {
    this.router.navigate(['']);
  }
}
