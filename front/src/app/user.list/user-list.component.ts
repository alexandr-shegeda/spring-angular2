import {Component, OnInit} from "@angular/core";
import {Response} from "@angular/http";
import {Router} from "@angular/router";
import * as Rx from "rxjs/Rx";
import "rxjs/add/operator/switchMap";
import {PaginationPage, PaginationPropertySort} from "../user.table/pagination";
import {Table} from "../user.table/table";
import {showLoading, hideLoading, doNothing} from "../commons";
import {UserService} from "../user.service/user.service";
import {User} from "../user/user";


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, Table<User> {

  userPage: PaginationPage<User>;
  self: Table<User>;

  constructor(private userService: UserService, private router: Router) {
    console.log(router);
  }

  ngOnInit() {
    let observable: Rx.Observable<PaginationPage<any>> = this.fetchPage(0, 10, null);
    showLoading();
    observable.subscribe(doNothing, hideLoading, hideLoading);
    this.self = this;
  }

  fetchPage(pageNumber: number, pageSize: number, sort: PaginationPropertySort): Rx.Observable<PaginationPage<User>> {
    let observable: Rx.Observable<PaginationPage<User>> = this.userService.findUser(pageNumber, pageSize, sort);
    observable.subscribe(userPage => this.userPage = userPage);
    return observable;
  }

  goToDetails(user) {
    console.log(user);
    this.router.navigate(['user', user.id]);
  }

  delete(user) {

    let observable: Rx.Observable<Response> = this.userService.deleteUser(user.id);
    showLoading();
    observable.switchMap(() => {
      return this.fetchPage(0, 10, null);
    }).subscribe(doNothing, hideLoading, hideLoading);
  }
}
