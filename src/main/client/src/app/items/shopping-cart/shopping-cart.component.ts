import {
	Component,
	EventEmitter,
	OnDestroy,
	OnInit,
	Output,
} from '@angular/core';
import { Subscription } from 'rxjs';
import { EcommerceService } from '../../services/ecommerce.service';
import { ProductOrder } from '../../models/product-order.model';
import { Product } from '../../models/product.model';
import { ProductOrders } from '../../models/product-orders.model';

@Component({
	selector: 'app-shopping-cart',
	templateUrl: './shopping-cart.component.html',
	styleUrls: ['./shopping-cart.component.scss'],
})
export class ShoppingCartComponent implements OnInit, OnDestroy {
	private sub: Subscription;
	orderFinished: boolean;
	total: number;
	orders: ProductOrders;
	selectedProductOrder: ProductOrder;

	@Output() onOrderFinished: EventEmitter<boolean>;

	constructor(private ecommerceService: EcommerceService) {
		this.total = 0;
		this.orderFinished = false;
		this.onOrderFinished = new EventEmitter<boolean>();
	}

	ngOnInit(): void {
		this.orders = new ProductOrders();
		this.loadCart();
		this.loadTotal();
	}

	ngOnDestroy() {
		this.sub.unsubscribe();
	}

	addToCart(order: ProductOrder) {
		this.ecommerceService.SelectedProductOrder = new ProductOrder(
			order.product,
			1
		);
		this.selectedProductOrder = this.ecommerceService.SelectedProductOrder;
	}

	removeFromCart(order: ProductOrder) {
		this.ecommerceService.SelectedProductOrder = new ProductOrder(
			order.product,
			-1
		);
		this.selectedProductOrder = this.ecommerceService.SelectedProductOrder;
	}

	loadTotal() {
		this.sub = this.ecommerceService.OrdersChanged$.subscribe(() => {
			this.total = this.calculateTotal(this.orders.productOrders);
		});
	}

	finishOrder() {
		this.ecommerceService.Total = this.total;
		this.orderFinished = true;
		this.onOrderFinished.emit(this.orderFinished);
	}

	getProductIndex(product: Product): number {
		return this.ecommerceService.ProductOrders.productOrders.findIndex(
			(value) => value.product === product
		);
	}

	private calculateTotal(products: ProductOrder[]): number {
		let sum = 0;
		products.forEach((value) => {
			sum += value.product.price * value.quantity;
		});
		return sum;
	}

	loadCart() {
		this.sub = this.ecommerceService.ProductOrderChanged$.subscribe(() => {
			let productOrder = this.ecommerceService.SelectedProductOrder;
			const exProd = this.orders.productOrders.find(
				(val) => val.product.name === productOrder.product.name
			);
			if (exProd) {
				exProd.quantity = exProd.quantity + productOrder.quantity;
				if (exProd.quantity == 0) {
					this.orders.productOrders.splice(
						this.getProductIndex(productOrder.product),
						1
					);
				}
			} else {
				this.orders.productOrders.push(
					new ProductOrder(productOrder.product, productOrder.quantity)
				);
			}
			this.ecommerceService.ProductOrders = this.orders;
			this.orders = this.ecommerceService.ProductOrders;
			this.total = this.calculateTotal(this.orders.productOrders);
		});
	}

	reset() {
		this.orderFinished = false;
		this.orders = new ProductOrders();
		this.orders.productOrders = [];
		this.loadTotal();
		this.total = 0;
	}
}
