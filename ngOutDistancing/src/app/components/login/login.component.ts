import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  closeResult = '';

  newUser = new User();
  selected = null;

  constructor(private authService:AuthService, private router: Router, private modalService: NgbModal) { }
  ngOnInit(): void {

  }


  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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
