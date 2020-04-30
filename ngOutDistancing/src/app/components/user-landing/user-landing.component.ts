import { Location } from './../../models/location';
import { AuthService } from './../../services/auth.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-user-landing',
  templateUrl: './user-landing.component.html',
  styleUrls: ['./user-landing.component.css']
})
export class UserLandingComponent implements OnInit {

  selected = null;
  newUser = new User();
  editUser = null;
  users: User[] = [];

  constructor(private userService: UserService,private currentRoute: ActivatedRoute, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.login("kissmyaxe","bullseye").subscribe(
      great =>{
        console.log("user landing Component ngOnInit TESTING LOGGED in")

      },
      terrible => {
        console.error("LoginComponent.login() Error in login component login method");
      }
    );
    this.reload();
  }

  reload(){
    this.userService.index().subscribe(
      data => {
        this.users = data;
      },
      bad => {
        //this.router.navigateByUrl('/login')
        console.error('TodoListComponent.reload(): error retrieving todo list');
        console.error(bad);

      }
    );
  }

}
