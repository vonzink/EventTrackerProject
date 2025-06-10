import { Status } from './../../models/status';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-status',
  imports: [],
  templateUrl: './status.html',
  styleUrl: './status.css',
  standalone: true,
  template: `status for {{ id }}`
})
export class StatusComponent {
  id!: number;
  constructor(private route: ActivatedRoute) {}
  ngOnInit() {
    this.id = +this.route.snapshot.params['id'];
  }
}


//   switch (this.StatusComponent) {
//        case 'lead':
//          searchObservable = this.applicationService.searchByApplication(this.searchValue);
//          break;
//       case 'application':
//         searchObservable = this.applicationService.searchByClearToClose(this.searchValue);
//         break;
//       case 'documentation':
//         searchObservable = this.applicationService.searchByStatus(this.searchValue);
//         break;
//       case 'underwriting':
//         searchObservable = this.applicationService.searchByLoanNumber(this.searchValue);
//         break;
//         case 'underwriting':
//         searchObservable = this.applicationService.searchByLoanNumber(this.searchValue);
//         break;
//         case 'underwriting':
//         searchObservable = this.applicationService.searchByLoanNumber(this.searchValue);
//         break;
//         case 'underwriting':
//         searchObservable = this.applicationService.searchByLoanNumber(this.searchValue);
//         break;
//       default:
//         this.loadApplications();
//         return;
//     }

//   }
// }
