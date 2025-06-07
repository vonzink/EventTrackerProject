import { CommonModule } from '@angular/common';
import { Application } from './../../models/application';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApplicationService } from '../../services/applicationService';

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
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Failed to load applications';
        this.loading = false;
        console.error('❌ Error retrieving applications:', err);
      }
    });
  }
selectedApp: Application | null = null;

viewApplication(app: Application): void {
  this.selectedApp = app;
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

  toggleTable(){
    this.showTable = !this.showTable; 
  }

  createNewLoan(): void {
    console.log('Create new loan');
    // Add router navigation when ready
  }
}
