import { Component, EventEmitter, OnInit, Output } from "@angular/core";
import { EcommerceService } from '../../services/ecommerce.service';
import { ProductOrders } from "../../models/product-orders.model";
import { Subscription } from "rxjs";

@Component({
	selector: 'app-orders',
	templateUrl: './orders.component.html',
	styleUrls: ['./orders.component.scss'],
})
export class OrdersComponent implements OnInit {
  private sub: Subscription;
  orders: ProductOrders;
  order: any;
  total: number;
  paid: boolean;
  hide: boolean;


  @Output() onOrderCanceled: EventEmitter<boolean>;
  @Output() onOrderFinished: EventEmitter<any>;

	constructor(private ecommerceService: EcommerceService) {
    this.hide = false;
	  this.orders = this.ecommerceService.ProductOrders;
    this.onOrderCanceled = new EventEmitter<boolean>();
    this.onOrderFinished = new EventEmitter<any>();
  }

	ngOnInit(): void {
    this.total = this.ecommerceService.Total;
    this.paid = false;
    this.sub = this.ecommerceService.OrdersChanged$.subscribe(() => {
      this.orders = this.ecommerceService.ProductOrders;
    });
	}

  pay() {
      this.paid = true;
      return this.ecommerceService
        .newOrder(
          this.orders
        )
        .subscribe(
          (result) => {
            this.order = result.newOrder;
          },
          (error) => {
            console.log(error)
          }
        );
  }

  back() {
    this.onOrderCanceled.emit(this.hide);
  }

  finish() {
    this.onOrderFinished.emit();
  }
}
