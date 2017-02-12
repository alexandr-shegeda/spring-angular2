import { Component, OnInit } from '@angular/core';
import {User} from '../user/user';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  user = {
    name: "",
    email: "",
    password: ""
  };

  constructor() {

  }

  ngOnInit() {
  }

  register(user) {
    console.log(user);
  }

  back() {
    history.back();
  }

}
