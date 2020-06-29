import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EcommerceService } from '../../services/ecommerce.service';
import { ItemsDialogComponent } from '../items-dialog/items-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { ProductOrder } from '../../models/product-order.model';
import { Subscription } from 'rxjs';
import { Product } from "../../models/product.model";

@Component({
	selector: 'app-product-details',
	templateUrl: './product-details.component.html',
	styleUrls: ['./product-details.component.scss'],
})
export class ProductDetailsComponent implements OnInit, OnDestroy {
	private subProductChange: Subscription;
	private subProduct: Subscription;
	item: Product = new Product();
	order: ProductOrder;
	selectedProductOrder: ProductOrder;
	deleteError: boolean;
	suggestedItems: any[] = [];

	constructor(
		private router: Router,
		private activatedRoute: ActivatedRoute,
		private ecommerceService: EcommerceService,
		private dialog: MatDialog
	) {}

	ngOnInit(): void {
		this.subProduct = this.activatedRoute.params.subscribe((params) => {
      this.getProductDetails(+params['id']);
		});

		this.subProductChange = this.ecommerceService.ProductChanged$.subscribe(
			() => {
				this.getProductDetails(this.ecommerceService.Product.id);
			}
		);
	}

  private getProductDetails(id: number) {
    this.ecommerceService.getProductDetails(id).subscribe((result) => {
      console.log(result)
      this.item = result.findProductById;
      this.suggestedItems = this.getSuggestedItems(
        this.getConcatProductOrders(
          result.findAllProductsFromOrdersByProductId
        ),
        JSON.stringify,
        id
      ).sort(() => 0.5 - Math.random());
    });
  }

	private getSuggestedItems(a, key, id) {
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

	ngOnDestroy(): void {
		this.subProductChange.unsubscribe();
		this.subProduct.unsubscribe();
	}

	private getConcatProductOrders(items: any[]) {
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
