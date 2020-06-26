import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { User } from '../models/user.model';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
	loginForm = new FormGroup({
		email: new FormControl('', [Validators.required]),
		password: new FormControl('', [Validators.required]),
	});
	subCurrentUser: Subscription;
	currentUser: User;
	error: any;
	errorMsg: string;

	constructor(
		private router: Router,
		private authenticationService: AuthenticationService
	) {}

	ngOnInit(): void {
		this.currentUser = this.authenticationService.getCurrentUser();
		this.subCurrentUser = this.authenticationService.CurrentUserChanged$.subscribe(
			() => {
				this.currentUser = this.authenticationService.getCurrentUser();
			}
		);
	}

	ngOnDestroy() {
		this.subCurrentUser.unsubscribe();
	}

	login(): void {
		this.authenticationService
			.login(
				this.loginForm.controls['email'].value,
				this.loginForm.controls['password'].value
			)
			.subscribe(
				(result) => {
					this.authenticationService.setCurrentUser(result.login);
					this.router.navigateByUrl('/');
				},
				(error) => {
					this.error = error;
					if (this.error.status === 200)
						this.errorMsg = 'Invalid login or password.';
					else
						this.errorMsg =
							'Sorry, we have occur an error and cannot proceed with login.';
				}
			);
	}
}
