import { UserService } from 'src/app/services/user.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

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
  isCollapsed = false;
  userImg = null;
  constructor(

    private authService: AuthService,
    private userService: UserService,
    private modalService: NgbModal,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getLoggedInUser();
    console.log(this.currUser);
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
        this.userImg = good.imageUrl;
        this.router.navigateByUrl("/userLanding");
        //this.newUser = new User();
      },
      bad => {
        console.log("Error logging in User:" + bad);
      });
  }

}
