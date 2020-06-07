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
	private productSub: Subscription;
	private suggestedSub: Subscription;
	item: Product;
	suggestedItems: Product[];
	deleteErrorMsg: boolean;

	constructor(
		private router: Router,
		private activatedRoute: ActivatedRoute,
		private ecommerceService: EcommerceService,
		private location: Location
	) {}

	ngOnInit(): void {
		this.productSub = this.activatedRoute.params.subscribe((params) => {
			this.ecommerceService
				.findProductById(+params['id'])
				.subscribe((result) => (this.item = result.findProductById));
		});
		this.suggestedSub = this.activatedRoute.params.subscribe((params) => {
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
		this.productSub.unsubscribe();
		this.suggestedSub.unsubscribe();
	}

	back() {
		this.location.back();
	}
}
