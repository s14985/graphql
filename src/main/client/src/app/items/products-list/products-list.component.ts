import { Component, Input, OnDestroy, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Product } from '../../models/product.model';
import { ProductOrder } from '../../models/product-order.model';
import { EcommerceService } from '../../services/ecommerce.service';

@Component({
	selector: 'app-products-list',
	templateUrl: './products-list.component.html',
	styleUrls: ['./products-list.component.scss'],
})
export class ProductsListComponent implements OnInit, OnDestroy {
	private sub: Subscription;
	product: Product;
	products: Product[];
	name: string;
	checker: boolean;
	errorMsg: boolean;


	constructor(
		private router: Router,
		private ecommerceService: EcommerceService
	) {}

	ngOnInit(): void {
    this.loadProducts();
    this.sub = this.ecommerceService.ProductChanged$.subscribe(() => {
      this.loadProducts();
      }
    )
	}

	ngOnDestroy(): void {
		this.sub.unsubscribe();
	}

	loadProducts() {
    this.ecommerceService
      .findAllProducts()
      .subscribe((result) => {
        this.products = result.findAllProducts
      });
  }
}
