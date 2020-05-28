import { User } from './user.model';

export class Order {
	id: number;
	dateCreated: any;
	status: string;
	user: User;

	constructor(id: number, dateCreated: any, status: string, user: User) {
		this.id = id;
		this.dateCreated = dateCreated;
		this.status = status;
		this.user = user;
	}
}
