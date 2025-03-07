import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css'],
})
export class EmployeeFormComponent {
  employeeForm: FormGroup;
  employeeId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.employeeForm = this.fb.group({
      name: ['', [Validators.required]],
      salary: ['', [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit(): void {
    this.employeeId = +this.route.snapshot.paramMap.get('id')!;

    if (this.employeeId) {
      this.employeeService.getEmployeeById(this.employeeId).subscribe((employee) => {
        this.employeeForm.patchValue(employee);
      });
    }
  }

  onSubmit(): void {
    if (this.employeeForm.valid) {
      if (this.employeeId) {
        this.employeeService.updateEmployee(this.employeeId, this.employeeForm.value).subscribe(() => {
          this.router.navigate(['/employee']);
        });
      } else {
        this.employeeService.addEmployee(this.employeeForm.value).subscribe(() => {
          this.router.navigate(['/employee']);
        });
      }
    }
  }

  goBack(): void {
    this.router.navigate(['/employee']);
  }
}
