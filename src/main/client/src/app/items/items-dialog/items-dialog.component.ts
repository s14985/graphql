import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Product } from '../../models/product.model';
import { Subscription } from 'rxjs';
import { EcommerceService } from '../../services/ecommerce.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
	selector: 'app-items-dialog',
	templateUrl: './items-dialog.component.html',
	styleUrls: ['./items-dialog.component.scss'],
})
export class ItemsDialogComponent implements OnInit {
	product: Product;
	subNewProduct: Subscription;
	saveErrorMsg: boolean;
	loadErrorMsg: boolean;

	addForm = new FormGroup({
		name: new FormControl(this.data === null ? '' : this.data.name, [
			Validators.required,
		]),
		price: new FormControl(this.data === null ? '' : this.data.price, [
			Validators.required,
		]),
		pictureUrl: new FormControl(this.data === null ? '' : this.data.picture, [
			Validators.required,
		]),
		details: new FormControl(this.data === null ? '' : this.data.details, [
			Validators.required,
		]),
	});

	name = this.addForm.controls['name'];
	price = this.addForm.controls['price'];
	picture = this.addForm.controls['pictureUrl'];
	details = this.addForm.controls['details'];

	constructor(
		private ecommerceService: EcommerceService,
		public dialog: MatDialogRef<ItemsDialogComponent>,
		@Inject(MAT_DIALOG_DATA) public data: Product
	) {}

	ngOnInit(): void {}

	onSubmit() {
		this.subNewProduct =
			this.data === null ? this.newProduct() : this.editProduct();
	}

	private editProduct() {
		return this.ecommerceService
			.editProduct(
				this.data.id,
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
						this.dialog.close(result.editProduct);
					}
				},
				(error) => {
					this.saveErrorMsg = true;
				}
			);
	}

	private newProduct() {
		return this.ecommerceService
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
