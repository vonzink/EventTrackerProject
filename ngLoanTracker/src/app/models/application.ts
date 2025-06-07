import { Borrower } from "./borrower";
import { Underwriting } from "./underwriting";

export class Application {
  id: number;
  loanNumber: number;
  propertyAddress: string;
  loanAmount: number;
  loanType: string;
  enable: boolean;
  underwriting: Underwriting;
  purpose: string;
  submittedDate: Date;
  status: string;
  borrower: Borrower;

  constructor(
    id: number = 0,
    loanNumber: number = 0,
    propertyAddress: string = '',
    loanAmount: number = 0,
    loanType: string = '',
    enable: boolean = true,
    underwriting?: Underwriting,
    purpose: string = '',
    submittedDate: Date = new Date(),
    status: string = '',
    borrower?: Borrower
  ) {
    this.id = id;
    this.loanNumber = loanNumber;
    this.propertyAddress = propertyAddress;
    this.loanAmount = loanAmount;
    this.loanType = loanType;
    this.enable = enable;
    this.underwriting = underwriting || new Underwriting();
    this.purpose = purpose;
    this.submittedDate = submittedDate;
    this.status = status;
    this.borrower = borrower || new Borrower();
  }
}
