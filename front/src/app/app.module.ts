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

const appRoutes: Routes = [
  // {path: 'operator/edit/:id', component: UserComponent},
  { path: 'main', component: UserListComponent},
  { path: 'operator/edit/:id', component: UserComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UserListComponent,
    TableElementsCountComponent,
    TablePaginationComponent,
    TableSortComponent
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
