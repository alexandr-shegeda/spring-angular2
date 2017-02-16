import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {UserComponent} from './user/user.component';
import {UserListComponent} from './user.list/user-list.component';
import {TableElementsCountComponent} from './user.table/counter/table-elements-count.component';
import {TablePaginationComponent} from './user.table/pagination/table-pagination.component';
import {TableSortComponent} from "./user.table/sort/table-sort.component";
import {UserService} from './user.service/user.service';
import { AppListComponent } from './app.list/app-list.component';
import { PublisherListComponent } from './publisher.list/publisher-list.component';
import { WelcomePageComponent } from './welcome.page/welcome-page.component';
import { RegistrationPageComponent } from './registration.page/registration-page.component';
import {LoginPageComponent} from "./login.page/login-page.component";

const appRoutes: Routes = [
  { path: '', component: WelcomePageComponent},
  { path: 'publisher', component: UserListComponent},
  { path: 'operator/edit/:id', component: UserComponent},
  { path: 'register', component: RegistrationPageComponent},
  { path: 'login', component: LoginPageComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UserListComponent,
    TableElementsCountComponent,
    TablePaginationComponent,
    TableSortComponent,
    AppListComponent,
    PublisherListComponent,
    WelcomePageComponent,
    LoginPageComponent,
    RegistrationPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
