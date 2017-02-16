import { Component, OnInit } from '@angular/core';
import {User} from "../user/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css']
})
export class WelcomePageComponent implements OnInit {

  public user: User;

  role = 'ROLE_ANONYMOUS';

  router: Router;

  constructor(router: Router) {
    this.router = router;
  }

  ngOnInit() {
  }

  goToUrl(url) {
    this.router.navigate([url]);
  }

}
