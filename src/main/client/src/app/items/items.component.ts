import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductsListComponent } from './products-list/products-list.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrderComponent } from './order/order.component';
import { ItemsDialogComponent } from './items-dialog/items-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { EcommerceService } from '../services/ecommerce.service';
import { Product } from '../models/product.model';

@Component({
	selector: 'app-items',
	templateUrl: './items.component.html',
	styleUrls: ['./items.component.scss'],
})
export class ItemsComponent implements OnInit {
	orderFinished = false;
	error: string;

	@ViewChild('productsList', { static: false })
	products: ProductsListComponent;

	@ViewChild('shoppingCart', { static: false })
	shoppingCart: ShoppingCartComponent;

	@ViewChild('orders', { static: false })
	orders: OrderComponent;

	constructor(
		private dialog: MatDialog,
		private ecommerceService: EcommerceService
	) {}

	ngOnInit(): void {}

	finishOrder(orderFinished: boolean) {
		this.orderFinished = orderFinished;
	}

	openDialog(item: Product) {
		const dialogRef = this.dialog.open(ItemsDialogComponent);
		dialogRef.componentInstance.data = item;
		dialogRef.afterClosed().subscribe((result) => {
			this.ecommerceService.Product = result;
		});
	}

	reset() {
		this.orderFinished = false;
		this.products.reset();
		this.shoppingCart.reset();
		this.orders.paid = false;
	}
}
