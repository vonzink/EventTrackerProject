import { Application } from "./application";

export class Underwriting {

  constructor(
    public underwriterName: string = '',
    public findings: string = '',
    public reviewDate: Date = new Date(),
    public decision: string = '',
    public application: Application = new Application()
  ) {

  }
}
