import { Product } from './product.model';

export class ProductOrder {
	id: number;
	product: Product;
	quantity: number;

	constructor(product: Product, quantity: number) {
		this.product = product;
		this.quantity = quantity;
	}
}
