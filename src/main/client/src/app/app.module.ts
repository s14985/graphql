import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderComponent } from './items/order/order.component';
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
import { ItemsDialogComponent } from './items/items-dialog/items-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { EcommerceService } from './services/ecommerce.service';
import { SuggestedProductComponent } from './items/product-details/suggested-product/suggested-product.component';

@NgModule({
	declarations: [
		AppComponent,
		OrderComponent,
		HomeComponent,
		NavbarComponent,
		FooterComponent,
		ErrorComponent,
		NavItemComponent,
		ProductDetailsComponent,
		ItemsComponent,
		ProductsListComponent,
		ShoppingCartComponent,
		ItemsDialogComponent,
		SuggestedProductComponent,
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		GraphQLModule,
		HttpClientModule,
		BrowserAnimationsModule,
		MatDialogModule,
		MatFormFieldModule,
		MatSelectModule,
		MatCheckboxModule,
		ReactiveFormsModule,
		MatInputModule,
		FormsModule,
	],
	providers: [EcommerceService],
	bootstrap: [AppComponent],
	entryComponents: [ItemsDialogComponent],
})
export class AppModule {}
