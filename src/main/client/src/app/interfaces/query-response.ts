import { Product } from './product';
import { Order } from './order';

export interface QueryResponse {
	findAllOrders: Order[];
	findProductById: Product;
	findAllProducts: Product[];
	findProductsFromOrdersByProductId: Product[];
	newProduct: Product;
}
