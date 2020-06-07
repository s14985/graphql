import { Order } from './order';
import { Product } from './product';

export interface ProductOrder {
	id: number;
	order: Order;
	product: Product;
	quantity: number;
}
