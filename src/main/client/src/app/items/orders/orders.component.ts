import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import gql from 'graphql-tag';
import { Apollo } from 'apollo-angular';

const findAllOrders = gql`
	query {
		findAllOrders {
			id
			status
			dateCreated
			orderProducts {
				id
			}
			user {
				id
			}
		}
	}
`;

@Component({
	selector: 'app-orders',
	templateUrl: './orders.component.html',
	styleUrls: ['./orders.component.scss'],
})
export class OrdersComponent implements OnInit, OnDestroy {
	private querySubscription: Subscription;
	loading: boolean;
	orders: any;

	constructor(private apollo: Apollo) {}

	ngOnInit(): void {
		this.querySubscription = this.apollo
			.watchQuery<any>({
				query: findAllOrders,
			})
			.valueChanges.subscribe(({ data, loading }) => {
				this.loading = loading;
				this.orders = data.findAllOrders;
			});
	}

	ngOnDestroy() {
		this.querySubscription.unsubscribe();
	}
}
