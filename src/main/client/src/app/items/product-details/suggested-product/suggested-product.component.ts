import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../../interfaces/product';
import { EcommerceService } from '../../../services/ecommerce.service';

@Component({
	selector: 'app-suggested-product',
	templateUrl: './suggested-product.component.html',
	styleUrls: ['./suggested-product.component.scss'],
})
export class SuggestedProductComponent implements OnInit {
	@Input() item: Product;

	constructor(private ecommerceService: EcommerceService) {}

	ngOnInit(): void {}
}
