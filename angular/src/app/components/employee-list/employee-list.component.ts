import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { EmployeeService} from '../../services/employee.service';
import { HttpClientModule } from '@angular/common/http';
import { Employee } from '../../models/zoo.model';

@Component({
  standalone: true,
  selector: 'app-employee-list',
  imports: [CommonModule, RouterModule, HttpClientModule],
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css'],
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];
  private employeeService = inject(EmployeeService);
  private router = inject(Router);

  ngOnInit() {
    this.loadEmployees();
  }

  loadEmployees() {
    this.employeeService.getEmployees().subscribe((data) => (this.employees = data));
  }

  addEmployee() {
    this.router.navigate(['/employee/new']);
  }

  editEmployee(id: number) {
    this.router.navigate([`/employee/${id}`]);
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe(() => {
      this.employees = this.employees.filter((employee) => employee.id !== id);
    });
  }

  goBack(): void {
    this.router.navigate(['']);
  }
}
