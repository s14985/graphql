import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { Subscription } from 'rxjs';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
	private subCurrentUser: Subscription;
	showImgContent: boolean = true;
	currentUserName: string;

	constructor(
		private router: Router,
		private authenticationService: AuthenticationService
	) {}

	ngOnInit(): void {
		if (window.innerWidth < 768) this.showImgContent = false;
		else this.showImgContent = true;

		if (this.authenticationService.getCurrentUser())
			this.currentUserName = this.authenticationService.getCurrentUser().firstName;
		else this.currentUserName = '';

		this.subCurrentUser = this.authenticationService.CurrentUserChanged$.subscribe(
			() => {
				if (this.authenticationService.getCurrentUser())
					this.currentUserName = this.authenticationService.getCurrentUser().firstName;
				else this.currentUserName = '';
			}
		);
	}

	ngOnDestroy(): void {
		this.subCurrentUser.unsubscribe();
	}

	shopNow() {
		this.router.navigate(['items']);
	}
}
