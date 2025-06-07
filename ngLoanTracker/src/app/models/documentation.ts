import { Application } from "./application";
import { User } from "./user";

export class Documentation {

  constructor(
      public id: number = 0,
      public application: Application = new Application(),
      public docType: string = '',
      public filePath: string = '',
      public uploadedBy: User = new User(),
      public uploadedAt: Date = new Date(),


    ) {}
  }

