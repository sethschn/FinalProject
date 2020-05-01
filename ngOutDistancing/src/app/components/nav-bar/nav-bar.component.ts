import { UserService } from 'src/app/services/user.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  currUser = null;
  isCollapsed = false;
  constructor(

    private authService: AuthService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
  }

  loggedIn(){
    return this.authService.checkLogin();
  }

  getLoggedInUser(){
    this.userService.showLoggedInUser().subscribe(
      data => {
        this.currUser = data;
      }
    )
  }

  adminLoggedIn(){
    if (this.loggedIn()){
     return this.authService.getCurrentUserRole() === 'admin';
    }
    return false;
  }

}
