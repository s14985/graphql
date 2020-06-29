import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-navbar',
	templateUrl: './navbar.component.html',
	styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
	collapsed = true;
	home = false;
	items = false;

	constructor() {}

	ngOnInit(): void {}

	toggleCollapsed(): void {
		this.collapsed = !this.collapsed;
	}
}
