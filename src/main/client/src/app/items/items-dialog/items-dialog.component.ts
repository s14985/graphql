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

	constructor(
		private ecommerceService: EcommerceService,
		public dialog: MatDialogRef<ItemsDialogComponent>
	) {}

	ngOnInit(): void {}

	onSubmit() {
		this.subNewProduct = this.ecommerceService
			.newProduct(
				this.addForm.controls['name'].value,
				this.addForm.controls['price'].value,
				this.addForm.controls['pictureUrl'].value,
				this.addForm.controls['details'].value
			)
			.subscribe(
				() => {
					if (
						!this.addForm.controls['name'].errors &&
						!this.addForm.controls['price'].errors &&
						!this.addForm.controls['pictureUrl'].errors &&
						!this.addForm.controls['details'].errors
					) {
						this.dialog.close(true);
					}
				},
				(error) => {
					this.saveErrorMsg = true;
				}
			);
	}
}
