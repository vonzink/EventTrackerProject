import { CommonModule } from '@angular/common';
import { Application } from './../../models/application';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApplicationService } from '../../services/applicationService';
import { Underwriting } from '../../models/underwriting';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit {
  applications: Application[] = [];
  loading: boolean = false;
  error: string = '';

  // Search state
  searchType: string = 'name'; // Default option
  searchValue: string = '';
  showEnabledOnly: boolean = true;
  showTable = true;
  selectedApp: Application | null = null;
  isEditing: boolean = false;

  // Search options
  searchOptions = [
    { value: 'name', label: 'Borrower Last Name' },
    { value: 'address', label: 'Property Address' },
    { value: 'status', label: 'Application Status' },
    { value: 'dateRange', label: 'Submitted Date Range' },
    { value: 'loanNumber', label: 'Loan Number' }
  ];

  // Add state for date range fields
  dateFrom: string = '';
  dateTo: string = '';

  constructor(private applicationService: ApplicationService) {}

  ngOnInit(): void {
    this.loadApplications();
  }

  loadApplications(): void {
    this.loading = true;
    this.error = '';

    const serviceCall = this.showEnabledOnly
      ? this.applicationService.getActiveApplications()
      : this.applicationService.index();

    serviceCall.subscribe({
      next: (data: Application[]) => {
        this.applications = data;
        console.log('Loaded applications:', data);
        console.log('Application IDs:', data.map(app => app.id));
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Failed to load applications';
        this.loading = false;
        console.error('❌ Error retrieving applications:', err);
      }
    });
  }

  editApplication(app: Application) {
    // Deep copy to avoid mutating table data until save
    this.selectedApp = JSON.parse(JSON.stringify(app));
    this.isEditing = true;
  }

  viewApplication(app: Application): void {
    this.selectedApp = app;
    this.isEditing = false;
  }

  performSearch(): void {
    if (!this.searchValue.trim()) {
      alert('Please enter a search term');
      return;
    }

    this.loading = true;
    this.error = '';

    let searchObservable;

    switch (this.searchType) {
      case 'name':
        searchObservable = this.applicationService.searchByName(this.searchValue);
        break;
      case 'address':
        searchObservable = this.applicationService.searchByAddress(this.searchValue);
        break;
      case 'status':
        searchObservable = this.applicationService.searchByStatus(this.searchValue);
        break;
      case 'loanNumber':
        searchObservable = this.applicationService.searchByLoanNumber(this.searchValue);
        break;
      default:
        this.loadApplications();
        return;
    }

    searchObservable.subscribe({
      next: (data: Application[]) => {
        this.applications = data;
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Search failed';
        this.loading = false;
        console.error('❌ Search error:', err);
      }
    });
  }

  clearSearch(): void {
    this.searchValue = '';
    this.loadApplications();
  }

  toggleEnabledFilter(): void {
    this.showEnabledOnly = !this.showEnabledOnly;
    this.loadApplications();
  }

  toggleTable() {
    this.showTable = !this.showTable;
  }

// home.ts
createNewLoan() {
  // Build a minimal payload that matches your JPA mapping
  this.selectedApp = {
    // omit id entirely (DB will generate it)
    loanNumber: null,               // Integer
    propertyAddress: '',            // String
    loanAmount: 0,                 // String (no “$”)
    loanType: '',                   // String
    purpose: '',                    // String
    submittedDate: new Date()
      .toISOString()
      .split('T')[0],               // 'YYYY-MM-DD'
    status: 'Submitted',            // String
    enable: true,                   // boolean
    borrower: {                      // Borrower entity only needs these
      firstName: '',
      lastName: '',
      email: '',
      phone: ''
    }
  } as any; // cast to satisfy TS

  this.isEditing = true;
}

  saveApplication() {
  if (this.selectedApp) {
    if (this.selectedApp.id && this.selectedApp.id !== 0) {
      // Existing: Update
      console.log('Updating application:', this.selectedApp);

      this.applicationService.updateLoan(this.selectedApp).subscribe({
        next: (updatedApp) => {
          console.log('Update response:', updatedApp);
          this.isEditing = false;
          this.selectedApp = null;
          this.loadApplications();
        },
        error: (err) => {
          this.error = 'Failed to update loan';
          console.error('Failed to update loan:', err);
        }
      });
    } else {
      // New: Create
      console.log('Creating new application:', this.selectedApp);

      this.applicationService.newLoan(this.selectedApp).subscribe({
        next: (createdApp) => {
          console.log('Create response:', createdApp);
          // Important: Update the selectedApp with the new ID from the server
          if (createdApp && createdApp.id) {
            this.selectedApp = createdApp;
          }
          this.isEditing = false;
          this.selectedApp = null;
          this.loadApplications();
        },
        error: (err) => {
          this.error = 'Failed to create loan';
          console.error('Failed to create loan:', err);
        }
      });
    }
  }
}
// File: src/app/components/home/home.ts

deleteApplication() {
  if (this.selectedApp && this.selectedApp.id && this.selectedApp.id !== 0) {
     console.log('Attempting to delete application with ID:', this.selectedApp.id);

    if (confirm('Are you sure you want to delete this loan application?')) {
      this.loading = true;
      this.error = '';

      this.applicationService.deleteLoan(this.selectedApp.id).subscribe({
        next: () => {
          console.log('✅ Application deleted successfully');
          this.isEditing = false;
          this.selectedApp = null;
          this.loadApplications();
          this.loading = false;
        },
        error: (err) => {
          this.error = 'Failed to delete application';
          console.error('❌ Failed to delete application:', err);
          this.loading = false;

          if (err.status === 404) {
            this.error = 'Application not found - it may have already been deleted';
            // Still refresh the list since it's not there
            this.loadApplications();
            this.selectedApp = null;
            this.isEditing = false;
            this.loadApplications();
          } else {
            this.error = 'Failed to delete applciation';
          }
          this.loading = false
        }
      });
    }
  }
}

cancelEdit() {
  this.isEditing = false;
  this.selectedApp = null;
}
}
