import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {
  signup: boolean = false;
  incorrectMail: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  emailIsValid(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
  }

  signUp(email: string) {
    if (this.emailIsValid(email)) {
      this.signup = true;
      this.incorrectMail = false;
    } else {
      this.signup = false;
      this.incorrectMail = true;
    }
  }
}
