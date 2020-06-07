import {
	Component,
	EventEmitter,
	OnDestroy,
	OnInit,
	Output,
} from '@angular/core';
import { Subscription } from 'rxjs';
import { EcommerceService } from '../../services/ecommerce.service';

@Component({
	selector: 'app-shopping-cart',
	templateUrl: './shopping-cart.component.html',
	styleUrls: ['./shopping-cart.component.scss'],
})
export class ShoppingCartComponent implements OnInit, OnDestroy {
	private sub: Subscription;
	orderFinished: boolean;
	total: number;

	@Output() onOrderFinished: EventEmitter<boolean>;

	constructor(private ecommerceService: EcommerceService) {
		this.total = 0;
		this.orderFinished = false;
		// this.onOrderFinished = new EventEmitter()<boolean>();
	}

	ngOnInit(): void {}

	ngOnDestroy() {
		// this.sub.unsubscribe();
	}
}
