import { Application } from "./application";

export class Declined {

 constructor(
        public id: number = 0,
        public application: Application = new Application(),
        public clearedBy: string = '',
        public ctcDate: Date = new Date(),
        public notes: string = ''
      ) {}
    }
