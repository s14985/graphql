import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EcommerceService } from '../../services/ecommerce.service';
import { Product } from '../../interfaces/product';
import { Subscription } from 'rxjs';
import { Location } from '@angular/common';

@Component({
	selector: 'app-product-details',
	templateUrl: './product-details.component.html',
	styleUrls: ['./product-details.component.scss'],
})
export class ProductDetailsComponent implements OnInit, OnDestroy {
	item: Product;
	suggestedItems: Product[];
	deleteError: boolean;

	constructor(
		private router: Router,
		private activatedRoute: ActivatedRoute,
		private ecommerceService: EcommerceService
	) {}

	ngOnInit(): void {
		this.activatedRoute.params.subscribe((params) => {
			this.ecommerceService
				.findProductById(+params['id'])
				.subscribe((result) => (this.item = result.findProductById));
		});

		this.activatedRoute.params.subscribe((params) => {
			this.ecommerceService
				.findProductsFromOrdersByProductId(+params['id'])
				.subscribe(
					(result) =>
						(this.suggestedItems = result.findProductsFromOrdersByProductId.sort(
							() => 0.5 - Math.random()
						))
				);
		});
	}

	ngOnDestroy(): void {
	}

  removeProduct() {
    this.activatedRoute.params.subscribe((params) => {
      this.ecommerceService
        .deleteProduct(+params['id'])
        .subscribe(
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

}
