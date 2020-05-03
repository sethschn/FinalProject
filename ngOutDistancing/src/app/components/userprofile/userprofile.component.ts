import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})

export class UserprofileComponent implements OnInit {
  closeResult = '';

  currentUser = null;
  editUser = null;

  constructor(private userService: UserService,private currentRoute: ActivatedRoute, private router: Router, private authService: AuthService, private modalService: NgbModal) { }


  ngOnInit(): void {
    this.reload();
  }

  setEditUser(){
    this.editUser = Object.assign({}, this.currentUser);
  }

  updateUser(user: User){
    this.userService.update(user).subscribe(
      yes => {
        this.reload();
        //this.currentUser = yes;
        this.editUser = null;
      },
      no => {
        console.error('UserProfileComponent.updateUser(): error');
        console.error(no);

      }
    );
    //this.todos = this.todoService.index();
  }

  reload(){
    this.userService.showLoggedInUser().subscribe(
      data => {
        this.currentUser = data;
        console.log(data);
      },
      error =>{
        console.log("error inside show logged in user");
      }
    );
  }

}
