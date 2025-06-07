import { Application } from './application';
import { User } from './user';

export class Status {

  constructor(
    public status: string = '',
    public changedAt: Date = new Date(),
    public notes: string = '',
    public changedBy: User = new User(),
    public application: Application = new Application()
  ) {

  }
}

