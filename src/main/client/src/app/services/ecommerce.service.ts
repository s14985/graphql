import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Apollo } from 'apollo-angular';
import {
	findAllOrders,
	findProductById,
	findAllProducts,
	findProductsFromOrdersByProductId,
	newProduct,
} from '../graphql_queries';
import { QueryResponse } from '../interfaces/query-response';

@Injectable({
	providedIn: 'root',
})
export class EcommerceService {
	constructor(private apollo: Apollo) {}

	findAllOrders(): Observable<QueryResponse> {
		return this.apollo
			.query<QueryResponse>({ query: findAllOrders })
			.pipe(map((result) => result.data));
	}

	findProductById(id: number): Observable<QueryResponse> {
		return this.apollo
			.query<QueryResponse>({ query: findProductById, variables: { id: id } })
			.pipe(map((result) => result.data));
	}

	findAllProducts(): Observable<QueryResponse> {
		return this.apollo
			.query<QueryResponse>({ query: findAllProducts })
			.pipe(map((result) => result.data));
	}

	findProductsFromOrdersByProductId(id: number): Observable<QueryResponse> {
		return this.apollo
			.query<QueryResponse>({
				query: findProductsFromOrdersByProductId,
				variables: { id: id },
			})
			.pipe(map((result) => result.data));
	}

	newProduct(
		name: string,
		price: number,
		picture: string,
		details: string
	): Observable<QueryResponse> {
		return this.apollo
			.mutate<QueryResponse>({
				mutation: newProduct,
				variables: {
					name: name,
					price: price,
					picture: picture,
					details: details,
				},
			})
			.pipe(map((result) => result.data));
	}
}
