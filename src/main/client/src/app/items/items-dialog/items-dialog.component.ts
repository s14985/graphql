import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Product } from '../../models/product.model';
import { Subscription } from 'rxjs';
import { EcommerceService } from '../../services/ecommerce.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
	selector: 'app-items-dialog',
	templateUrl: './items-dialog.component.html',
	styleUrls: ['./items-dialog.component.scss'],
})
export class ItemsDialogComponent implements OnInit {
	id: number;
	product: Product;
	subNewProduct: Subscription;
	saveErrorMsg: boolean;
	loadErrorMsg: boolean;

	addForm = new FormGroup({
		name: new FormControl('', [Validators.required]),
		price: new FormControl('', [Validators.required]),
		pictureUrl: new FormControl('', [Validators.required]),
		details: new FormControl('', [Validators.required]),
	});

  name = this.addForm.controls['name'];
  price = this.addForm.controls['price'];
  picture = this.addForm.controls['pictureUrl'];
  details = this.addForm.controls['details'];

	constructor(
		private ecommerceService: EcommerceService,
		public dialog: MatDialogRef<ItemsDialogComponent>
	) {}

	ngOnInit(): void {}

	onSubmit() {
		this.subNewProduct = this.ecommerceService
			.newProduct(
				this.name.value,
				this.price.value,
				this.picture.value,
				this.details.value
			)
			.subscribe(
				(result) => {
					if (
						!this.name.errors &&
						!this.price.errors &&
						!this.picture.errors &&
						!this.details.errors
					) {
						this.dialog.close(result.newProduct);
					}
				},
				(error) => {
					this.saveErrorMsg = true;
				}
			);
	}
}
