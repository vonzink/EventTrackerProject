import { Application } from "./application";

export class Borrower {

  id: number;
  borrower: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  createdAt: Date;
  applications: Application[];

constructor(
    id: number = 0,
    borrower: string = '',
    firstName: string = '',
    lastName: string = '',
    email: string = '',
    phone: string = '',
    createdAt: Date = new Date(),
    applications: Application[] = []
  ) {
    this.id = id;
    this.borrower = borrower;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.createdAt = createdAt;
    this.applications = applications;
  }
}
