import { Component, OnInit } from '@angular/core';
import {User} from "../user/user";

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css']
})
export class WelcomePageComponent implements OnInit {

  public user: User;

  role = 'ROLE_ANONYMOUS';

  constructor() {}

  ngOnInit() {
  }

}
