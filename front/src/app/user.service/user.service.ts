import {Injectable} from "@angular/core";
import {User} from "../user/user";
import {PaginationPage, PaginationPropertySort} from "../user.table/pagination";
import {webServiceEndpoint} from "../commons";
import {Http, Response, URLSearchParams, RequestOptions, Headers} from "@angular/http";
import * as Rx from "rxjs/Rx";
import "rxjs/add/operator/map";
import "rxjs/add/operator/publish";

@Injectable()
export class UserService {

  constructor(private http: Http) {

  }

  register(user): Rx.Observable<Response> {
    // let headers = new Headers();
    // headers.append("Access-Control-Allow-Origin", "*");
    // headers.append("Content-Type", "application/json");
    //
    // let options = new RequestOptions(
    //   {headers: headers}
    // );

    return this.http.post(`${webServiceEndpoint}/api/register`, user).map((response: Response) => response.json());
  }

  login(user): Rx.Observable<Response> {
    let headers = new Headers();
    headers.append("Access-Control-Allow-Origin", "*");
    headers.append("Authorization", "Basic " + btoa(user.email + ":" + user.password));


    let options = new RequestOptions(
      {headers}
    );

    return this.http.get(`${webServiceEndpoint}/api/login`, options).map((response: Response) => {
      let result = response.json();
      console.log(result);
      return result;
    });
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
    return this.http.get(`${webServiceEndpoint}/api/operator`, options).map(this.extractData).publish().refCount();
  }

  getAllUsers(page: number, pageSize: number, sort: PaginationPropertySort): Rx.Observable<PaginationPage<User>> {
    let params = new URLSearchParams();
    params.set('size', `${pageSize}`);
    params.set('page', `${page}`);

    let options = new RequestOptions({
      search: params
    });
    return this.http.get(`${webServiceEndpoint}/api/operator`, options).map(this.extractData).publish().refCount();
  }

  getUser(id: number): Rx.Observable<User> {
    return this.http.get(`${webServiceEndpoint}/api/operator/edit/${id}`).map(this.extractData).publish().refCount();
  }

  deleteUser(id: number): Rx.Observable<Response> {
    return this.http.delete(`${webServiceEndpoint}/api/operator/delete/${id}`).publish().refCount();
  }

  private extractData(res: Response) {
    let body = res.json();
    console.log(body);
    return body || {};
  }
}
