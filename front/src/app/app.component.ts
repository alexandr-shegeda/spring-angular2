import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app works!';

  Users = [
    {name: "bob", age: 21},
    {name: "sara", age: 23}
  ];

  addUser() {
    this.Users.push({name: "Pushed", age: 100});
  }
}
