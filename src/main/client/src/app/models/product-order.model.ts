import { Order } from './order.model';
import { Product } from './product.model';

export class ProductOrder {
	id: number;
	order: Order;
	product: Product;
	quantity: number;

	constructor(order: Order, product: Product, quantity: number) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}
}
