import { Product } from "../models/product.model";
import { Order } from "../models/order.model";
import { User } from "../models/user.model";

export interface QueryResponse {
	findAllOrders: Order[];
	findProductById: Product;
	findAllProducts: Product[];
	findProductsFromOrdersByProductId: Product[];
	newProduct: Product;
	deleteProduct: boolean;
	findAllProductsFromOrdersByProductId: Product[];
	editProduct: Product;
	newOrder: Order;
	login: User;
}
