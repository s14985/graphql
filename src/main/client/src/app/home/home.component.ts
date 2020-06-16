import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  showImgContent: boolean = true;

	constructor(private router: Router) {}

	ngOnInit(): void {
    if (window.innerWidth < 768)
      this.showImgContent = false;
    else
      this.showImgContent = true;
  }

  shopNow() {
    this.router.navigate(['items']);
  }
}
