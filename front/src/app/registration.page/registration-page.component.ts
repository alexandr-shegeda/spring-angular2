import {Component, OnInit} from "@angular/core";
import {UserService} from "../user.service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  model: any = {};
  protected errorMessage: string;

  constructor(private userService: UserService, private router: Router) {

  }

  ngOnInit() {
  }

  register() {
    this.errorMessage = null;
    this.userService.register(this.model).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['/login']);
      },
      error => {
        console.log(error);
        let response = error.json();
        console.log(response);
        this.errorMessage = response;

        setTimeout(function () {
          this.errorMessage = null;
        }.bind(this), 3000);
      }
    );
  }

}
