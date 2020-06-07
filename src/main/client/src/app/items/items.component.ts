import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductsListComponent } from './products-list/products-list.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrdersComponent } from './orders/orders.component';
import { ItemsDialogComponent } from './items-dialog/items-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
	selector: 'app-items',
	templateUrl: './items.component.html',
	styleUrls: ['./items.component.scss'],
})
export class ItemsComponent implements OnInit {
	orderFinished = false;
	error: string;

	@ViewChild('productsList', { static: false })
	productsC: ProductsListComponent;

	@ViewChild('shoppingCart', { static: false })
	shoppingCartC: ShoppingCartComponent;

	@ViewChild('orders', { static: false })
	ordersC: OrdersComponent;

	constructor(public dialog: MatDialog) {}

	ngOnInit(): void {}

	finishOrder(orderFinished: boolean) {
		this.orderFinished = orderFinished;
	}

	openDialog() {
		const dialogRef = this.dialog.open(ItemsDialogComponent);

		dialogRef.afterClosed().subscribe((result) => {
			console.log(`Dialog result: ${result}`);
		});
	}
}
