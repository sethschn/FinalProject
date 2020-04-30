import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  newUser = new User();
  selected = null;

  constructor(private authService:AuthService, private router: Router) { }
  ngOnInit(): void {
  }
  login(user:User){
    this.newUser = user;
    console.log(this.newUser);
    this.authService.login(user.username, user.password).subscribe(
      good => {
        console.log(good);
        this.router.navigateByUrl("/userLanding");
      })
      this.newUser = new User();
      bad => {
        console.log("Error posting new User:" + bad);
      }
  }
}
