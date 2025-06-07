import { Application } from "./application";

export class Funded {

  constructor(
    public application: Application = new Application(),
    public fundedDate: Date = new Date(),
    public wireAmount: number = 0,
    public wireConfirmation: string = ''
  ) {

  }
}
