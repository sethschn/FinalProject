import { UserService } from 'src/app/services/user.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { Location } from './../../models/location';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  closeResult = '';
  currUser = null;
  newUser = new User();
  selected = null;
  isCollapsed = true;
  userImg = null;
  userLocation = new Location();
  returnedLocation = null;
  constructor(

    private authService: AuthService,
    private userService: UserService,
    private modalService: NgbModal,
    private router: Router,
    private locaSvc: LocationService
  ) { }

  ngOnInit(): void {
    console.log("NavBar ngOnInit");

    if (this.loggedIn()){
      console.log("logged in");
      this.getLoggedInUser();
    }
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

  open(content) {
    this.modalService.open(content, {centered: true, ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  openLg(content) {
    this.modalService.open(content, { size: 'lg' });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  login(user:User){
    this.authService.login(user.username, user.password).subscribe(
      good => {
        console.log(good);
        this.currUser = good;
        this.router.navigateByUrl("/userLanding");
        //this.newUser = new User();
      },
      bad => {
        console.log("Error logging in User:" + bad);
      });
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
        this.authService.register(user).subscribe(
          good => {
            this.authService.login(user.username,user.password).subscribe(
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
