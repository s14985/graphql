import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EcommerceService } from '../../services/ecommerce.service';
import { Product } from '../../interfaces/product';
import { ItemsDialogComponent } from '../items-dialog/items-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { ProductOrder } from '../../models/product-order.model';
import { Subscription } from 'rxjs';

@Component({
	selector: 'app-product-details',
	templateUrl: './product-details.component.html',
	styleUrls: ['./product-details.component.scss'],
})
export class ProductDetailsComponent implements OnInit, OnDestroy {
	private subProduct: Subscription;
	private subRoute: Subscription;
	item: Product;
	order: ProductOrder;
	selectedProductOrder: ProductOrder;
	deleteError: boolean;
	suggestedItems: any[];

	constructor(
		private router: Router,
		private activatedRoute: ActivatedRoute,
		private ecommerceService: EcommerceService,
		private dialog: MatDialog
	) {}

	ngOnInit(): void {
		this.subRoute = this.activatedRoute.params.subscribe((params) => {
			this.loadProduct(+params['id']);
		});

		this.subProduct = this.ecommerceService.ProductChanged$.subscribe(() => {
			this.loadProduct(this.ecommerceService.Product.id);
		});

		this.subRoute = this.activatedRoute.params.subscribe((params) => {
			this.ecommerceService
				.findAllProductsFromOrdersByProductId(+params['id'])
				.subscribe((result) => {
					this.suggestedItems = this.getSuggestedItems(
						this.getConcatProductOrders(
							result.findAllProductsFromOrdersByProductId
						),
						JSON.stringify,
						+params['id']
					).sort(() => 0.5 - Math.random());
				});
		});
	}

	getSuggestedItems(a, key, id) {
		const seen = {};
		return a.filter(function (item) {
			const k = key(item);
			return item.product.id == id
				? false
				: seen.hasOwnProperty(k)
				? false
				: (seen[k] = true);
		});
	}
	private loadProduct(id: number) {
		this.ecommerceService
			.findProductById(id)
			.subscribe((result) => (this.item = result.findProductById));
	}

	ngOnDestroy(): void {
		this.subProduct.unsubscribe();
		this.subRoute.unsubscribe();
	}

	getConcatProductOrders(items: any[]) {
		let m = [];
		for (let i = 0; i < items.length; i++) {
			m = [].concat.apply(m, items[i].order.productOrders);
		}
		return m;
	}

	removeProduct() {
		this.activatedRoute.params.subscribe((params) => {
			this.ecommerceService.deleteProduct(+params['id']).subscribe(
				(result) => {
					this.deleteError = !result.deleteProduct;
					this.router.navigateByUrl('/items');
				},
				(error) => {
					this.deleteError = true;
				}
			);
		});
	}

	addToCart(item: Product) {
		this.order = new ProductOrder(item, 1);
		this.ecommerceService.SelectedProductOrder = this.order;
		this.selectedProductOrder = this.ecommerceService.SelectedProductOrder;
	}

	openDialog(item: Product) {
		const dialogRef = this.dialog.open(ItemsDialogComponent, { data: item });
		dialogRef.afterClosed().subscribe((result) => {
			this.ecommerceService.Product = result;
		});
	}
}
