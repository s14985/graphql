import { Component, Input, OnInit } from '@angular/core';
import { Product } from "../../../models/product.model";

@Component({
	selector: 'app-suggested-product',
	templateUrl: './suggested-product.component.html',
	styleUrls: ['./suggested-product.component.scss'],
})
export class SuggestedProductComponent implements OnInit {
	@Input() item: Product;

	constructor() {}

	ngOnInit(): void {}
}
