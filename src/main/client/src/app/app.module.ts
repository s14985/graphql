import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrdersComponent } from './items/orders/orders.component';
import { GraphQLModule } from './graphql.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { ErrorComponent } from './shared/error/error.component';
import { NavItemComponent } from './shared/navbar/nav-item/nav-item.component';
import { ProductDetailsComponent } from './items/product-details/product-details.component';
import { ItemsComponent } from './items/items.component';
import { ProductsListComponent } from './items/products-list/products-list.component';
import { ShoppingCartComponent } from './items/shopping-cart/shopping-cart.component';

@NgModule({
	declarations: [AppComponent, OrdersComponent, HomeComponent, NavbarComponent, FooterComponent, ErrorComponent, NavItemComponent, ProductDetailsComponent, ItemsComponent, ProductsListComponent, ShoppingCartComponent],
	imports: [
		BrowserModule,
		AppRoutingModule,
		GraphQLModule,
		HttpClientModule,
		BrowserAnimationsModule,
	],
	providers: [],
	bootstrap: [AppComponent],
})
export class AppModule {}
