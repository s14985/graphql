import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductsListComponent } from './products-list/products-list.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrdersComponent } from './orders/orders.component';
import { ItemsDialogComponent } from './items-dialog/items-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { EcommerceService } from "../services/ecommerce.service";
import { Product } from "../models/product.model";

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

	constructor(private dialog: MatDialog, private ecommerceService: EcommerceService) {}

	ngOnInit(): void {}

	finishOrder(orderFinished: boolean) {
		this.orderFinished = orderFinished;
	}

	openDialog() {
		const dialogRef = this.dialog.open(ItemsDialogComponent);
		dialogRef.afterClosed().subscribe((result) => {
      this.ecommerceService.setNewProduct(result);
		});
	}
}
