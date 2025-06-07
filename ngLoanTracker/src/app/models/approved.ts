import { Application } from "./application";

export class Approved {

  constructor(
    public id: number = 0,
    public approvalDate: number = Date.now(),
    public approvalNotes: string = '',
    public interestRate: number = 0,
    public termYears: number = 0,
    public application: Application = new Application()
  ) {}
}
