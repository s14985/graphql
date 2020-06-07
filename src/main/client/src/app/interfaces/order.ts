import { User } from './user';

export interface Order {
	id: number;
	dateCreated: any;
	status: string;
	user: User;
}
