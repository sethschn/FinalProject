import { LocationService } from './../../services/location.service';
import { Location } from './../../models/location';
import { UserService } from 'src/app/services/user.service';
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
  userLocation = new Location();
  returnedLocation = null;


  constructor(private auth: AuthService, private router: Router, private userSvc: UserService, private locaSvc: LocationService) { }

  ngOnInit(): void {
  }

  register(user: User, location: Location){
    console.log(user);
    console.log(location);
    this.locaSvc.createLocation(location).subscribe(
      location => {
        console.log("Create location");
        console.log(location);
        this.returnedLocation = location;
        user.location = this.returnedLocation;
        this.auth.register(user).subscribe(
          good => {
            this.auth.login(user.username,user.password).subscribe(
              great =>{
                this.router.navigateByUrl("/userLanding");
                console.log(great);
              },
              terrible => {
                console.error("RegisterComponent.login() Error in login after register");
                console.error(terrible);
              }
            );
          },
          bad => {
            console.error("RegisterComponent.register() method error");
            console.error(bad);
          }
        );

      },
      err =>{
        console.log("Error creating location in user register "+err);
      }
    );
  }

}
