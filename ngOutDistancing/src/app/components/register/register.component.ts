import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser = new User();
  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  register(user:User){
    this.newUser = user;
    console.log(this.newUser);
    this.auth.register(user).subscribe(
      good => {
        console.log(good);
        this.router.navigateByUrl("/userLanding");
      })
      this.newUser = new User();
      bad => {
        console.log("Error registering new User:" + bad);
      }
  }

}
