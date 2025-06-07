import { Application } from './application';

export class Closing {
  constructor(
    public id: number = 0,
    public closingDate: Date = new Date(),
    public settlementAgent: string = '',
    public notes: string = '',
    public application: Application = new Application()
  ) {}
}
