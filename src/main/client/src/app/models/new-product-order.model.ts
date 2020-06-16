export class NewProductOrder {
	private id: number;
	private productId: number;
	private quantity: number;

	constructor(productId: number, quantity: number) {
		this.productId = productId;
		this.quantity = quantity;
	}
}
