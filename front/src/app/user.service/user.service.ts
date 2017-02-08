import {Injectable} from '@angular/core';
import {User} from '../user/user'
import {PaginationPage, PaginationPropertySort} from '../user.table/pagination';
import {webServiceEndpoint} from '../commons';
import {Http, Response, URLSearchParams, RequestOptions} from '@angular/http';
import * as Rx from "rxjs/Rx";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/publish';

@Injectable()
export class UserService {

  constructor(private http: Http) {

  }

  findUser(page: number, pageSize: number, sort: PaginationPropertySort): Rx.Observable<PaginationPage<User>> {
    let params = new URLSearchParams();
    params.set('size', `${pageSize}`);
    params.set('page', `${page}`);
    if (sort != null) {
      params.set('sort', `${sort.property},${sort.direction}`);
    }

    let options = new RequestOptions({
      search: params
    });
    return this.http.get(`${webServiceEndpoint}/operator`, options).map(this.extractData).publish().refCount();
  }

  getAllUsers(page: number, pageSize: number, sort: PaginationPropertySort): Rx.Observable<PaginationPage<User>> {
    let params = new URLSearchParams();
    params.set('size', `${pageSize}`);
    params.set('page', `${page}`);

    let options = new RequestOptions({
      search: params
    });
    return this.http.get(`${webServiceEndpoint}/operator`, options).map(this.extractData).publish().refCount();
  }

  getUser(id: number): Rx.Observable<User> {
    return this.http.get(`${webServiceEndpoint}/operator/${id}`).map(this.extractData).publish().refCount();
  }

  deleteUser(id: number): Rx.Observable<Response> {
    return this.http.delete(`${webServiceEndpoint}/operator/${id}`).publish().refCount();
  }

  private extractData(res: Response) {
    let body = res.json();
    console.log(body);
    return body || {};
  }
}
