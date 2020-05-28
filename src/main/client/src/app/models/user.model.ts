export class User {
	id: number;
	email: string;
	password: string;
	userType: string;

	constructor(id: number, email: string, password: string, userType: string) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
}
