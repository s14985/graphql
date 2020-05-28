import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrdersComponent } from './orders/orders.component';
import { GraphQLModule } from './graphql.module';

const routes: Routes = [
	{
		path: 'orders',
		component: OrdersComponent,
	},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
	providers: [GraphQLModule],
})
export class AppRoutingModule {}
