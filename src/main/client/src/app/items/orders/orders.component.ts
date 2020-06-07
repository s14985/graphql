import { Component, OnInit } from '@angular/core';
import { Order } from '../../interfaces/order';
import { EcommerceService } from '../../services/ecommerce.service';
import { Product } from '../../interfaces/product';
import { ProductOrder } from '../../models/product-order.model';

@Component({
	selector: 'app-orders',
	templateUrl: './orders.component.html',
	styleUrls: ['./orders.component.scss'],
})
export class OrdersComponent implements OnInit {
	orders: Order[];

	constructor(private ecommerceService: EcommerceService) {}

	ngOnInit(): void {
		this.ecommerceService
			.findAllOrders()
			.subscribe((result) => (this.orders = result.findAllOrders));
	}
}
