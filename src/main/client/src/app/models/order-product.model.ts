import { Product } from './product.model';
import { Order } from './order.model';

export class OrderProduct {
	id: number;
	product: Product;
	order: Order;
	quantity: number;

	constructor(id: number, product: Product, order: Order, quantity: number) {
		this.id = id;
		this.product = product;
		this.order = order;
		this.quantity = quantity;
	}
}
