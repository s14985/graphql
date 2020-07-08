import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { Apollo } from 'apollo-angular';
import {
	findAllProducts,
	newProduct,
	deleteProduct,
	editProduct,
	newOrder,
	getProductDetails,
	finishOrder,
} from '../graphql_queries';
import { QueryResponse } from '../interfaces/query-response';
import { Product } from '../models/product.model';
import { ProductOrder } from '../models/product-order.model';
import { ProductOrders } from '../models/product-orders.model';
import { NewProductOrder } from '../models/new-product-order.model';

@Injectable({
	providedIn: 'root',
})
export class EcommerceService {
	constructor(private apollo: Apollo) {}

	private product: Product;
	private productOrder: ProductOrder;
	private orders: ProductOrders = new ProductOrders();
	private total: number;

	private _productSubject = new Subject<Product>();
	private _productOrderSubject = new Subject<ProductOrder>();
	private _ordersSubject = new Subject();
	private _totalSubject = new Subject();

	ProductChanged$ = this._productSubject.asObservable();
	ProductOrderChanged$ = this._productOrderSubject.asObservable();
	OrdersChanged$ = this._ordersSubject.asObservable();
	TotalChanged$ = this._totalSubject.asObservable();

	set Product(value: Product) {
		this.product = value;
		this._productSubject.next();
	}

	get Product() {
		return this.product;
	}

	set SelectedProductOrder(value: ProductOrder) {
		this.productOrder = value;
		this._productOrderSubject.next();
	}

	get SelectedProductOrder() {
		return this.productOrder;
	}

	set ProductOrders(value: ProductOrders) {
		this.orders = value;
		this._ordersSubject.next();
	}

	get ProductOrders() {
		return this.orders;
	}

	get Total() {
		return this.total;
	}

	set Total(value: number) {
		this.total = value;
		this._totalSubject.next();
	}

	findAllProducts(): Observable<QueryResponse> {
		return this.apollo
			.watchQuery<QueryResponse>({ query: findAllProducts })
			.valueChanges.pipe(map((result) => result.data));
	}

	newProduct(
		name: string,
		price: number,
		picture: string,
		details: string,
		manufacturer: string,
		itemCode: string,
		color: string,
		material: string
	): Observable<QueryResponse> {
		return this.apollo
			.mutate<QueryResponse>({
				mutation: newProduct,
				variables: {
					name: name,
					price: price,
					picture: picture,
					details: details,
					manufacturer: manufacturer,
					itemCode: itemCode,
					color: color,
					material: material,
				},
			})
			.pipe(map((result) => result.data));
	}

	deleteProduct(id: number): Observable<QueryResponse> {
		return this.apollo
			.mutate<QueryResponse>({
				mutation: deleteProduct,
				variables: {
					id: id,
				},
			})
			.pipe(map((result) => result.data));
	}

	editProduct(
		id: number,
		name: string,
		price: number,
		picture: string,
		details: string,
		manufacturer: string,
		itemCode: string,
		color: string,
		material: string
	): Observable<QueryResponse> {
		return this.apollo
			.mutate<QueryResponse>({
				mutation: editProduct,
				variables: {
					id: id,
					name: name,
					price: price,
					picture: picture,
					details: details,
					manufacturer: manufacturer,
					itemCode: itemCode,
					color: color,
					material: material,
				},
			})
			.pipe(map((result) => result.data));
	}

	newOrder(productOrders: ProductOrders): Observable<QueryResponse> {
		return this.apollo
			.mutate<QueryResponse>({
				mutation: newOrder,
				variables: {
					productOrders: productOrders.productOrders.map(
						(value) => new NewProductOrder(value.product.id, value.quantity)
					),
				},
			})
			.pipe(map((result) => result.data));
	}

	getProductDetails(id: number): Observable<QueryResponse> {
		return this.apollo
			.watchQuery<QueryResponse>({
				query: getProductDetails,
				variables: { id: id },
			})
			.valueChanges.pipe(map((result) => result.data));
	}

	finishOrder(id: number): Observable<QueryResponse> {
		return this.apollo
			.mutate<QueryResponse>({
				mutation: finishOrder,
				variables: { id: id },
			})
			.pipe(map((result) => result.data));
	}
}
