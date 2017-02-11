import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import {Response} from '@angular/http';
import * as Rx from 'rxjs/Rx';

import {UserService} from '../user.service/user.service';
import {User} from './user';
import {showLoading, hideLoading, doNothing} from '../commons'

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css', '../bootstrap.min.css']
})
export class UserComponent implements OnInit {

  user: User;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) {

  }

  ngOnInit() {
    this.route.params.subscribe(params=> {
      this.userService.getUser(Number(params['id'])).subscribe(user => this.user = user);
    });

  }

  delete(user) {
    let observable: Rx.Observable<Response> = this.userService.deleteUser(user.id);
    showLoading();
    observable.subscribe(doNothing, hideLoading, ()=> {
      this.router.navigate(['']);
      hideLoading();
    });
  }

  back() {
    history.back();
  }
}
