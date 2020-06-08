import { Injectable } from '@angular/core';
import { Observable, Subject } from "rxjs";
import { map } from 'rxjs/operators';
import { Apollo } from 'apollo-angular';
import {
	findAllOrders,
	findProductById,
	findAllProducts,
	findProductsFromOrdersByProductId,
	newProduct,
  deleteProduct,
} from '../graphql_queries';
import { QueryResponse } from '../interfaces/query-response';
import { Product } from "../models/product.model";


@Injectable({
	providedIn: 'root',
})
export class EcommerceService {
	constructor(private apollo: Apollo) {}


  private product: Product;

	private _productSubject = new Subject<Product>();

  ProductChanged$ = this._productSubject.asObservable();

  setNewProduct(product: Product) {
    this.product = product;
    this._productSubject.next(product);
  }


	findAllOrders(): Observable<QueryResponse> {
		return this.apollo
			.watchQuery<QueryResponse>({ query: findAllOrders }).valueChanges
			.pipe(map((result) => result.data));
	}

	findProductById(id: number): Observable<QueryResponse> {
		return this.apollo
			.watchQuery<QueryResponse>({ query: findProductById, variables: { id: id } }).valueChanges
			.pipe(map((result) => result.data));
	}

	findAllProducts(): Observable<QueryResponse> {
		return this.apollo
			.watchQuery<QueryResponse>({ query: findAllProducts }).valueChanges
			.pipe(map((result) => result.data));
	}

	findProductsFromOrdersByProductId(id: number): Observable<QueryResponse> {
		return this.apollo
			.watchQuery<QueryResponse>({
				query: findProductsFromOrdersByProductId,
				variables: { id: id },
			}).valueChanges
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

  deleteProduct(
    id: number
  ): Observable<QueryResponse> {
    return this.apollo.mutate<QueryResponse>({
      mutation: deleteProduct,
      variables: {
        id: id
      }
    })
      .pipe(map((result) => result.data));

  }
}
