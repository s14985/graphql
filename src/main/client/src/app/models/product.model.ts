export class Product {
	id: number;
	name: string;
	price: number;
	picture: string;
	details: string;

	constructor(
		id: number,
		name: string,
		price: number,
		picture: string,
		details: string
	) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.details = details;
	}
}
