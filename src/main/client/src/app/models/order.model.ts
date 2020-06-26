import { User } from "./user.model";

export class Order {
  id: number;
  dateCreated: any;
  status: string;
  user: User;
}
