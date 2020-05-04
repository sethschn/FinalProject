import { UsergroupService } from './../../services/usergroup.service';
import { Usergroup } from './../../models/usergroup';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-usergroup',
  templateUrl: './usergroup.component.html',
  styleUrls: ['./usergroup.component.css']
})
export class UsergroupComponent implements OnInit {
  //group = new Usergroup();
  currentUser = null;
  groups: Usergroup[] = [];
  creator: User = null;
  newGroup = new Usergroup();
  selected = null;
  editGroup = null;

  constructor( private authService: AuthService,
    private userService: UserService,
    private groupService: UsergroupService,
    private modalService: NgbModal,
    private router: Router) { }

  ngOnInit(): void {
    this.loadGroups();
  }

  createGroup(){

  }

  loadGroups(){
    this.groupService.index().subscribe(
      data => {
        console.log(data);
        this.groups = data;
      },
      err => {
        console.error('Error in our loadGroups() method. ' + err);
      }
    );
  }


  updateGroup(group: Usergroup){
    console.log(group);
    this.groupService.update(group).subscribe(
      yes => {
        this.selected = yes;
        this.loadGroups();
        this.editGroup = null;
      },
      no => {
        console.error('UserGroupComponent.updateGroup(): error');
        console.error(no);

      }
    );
    //this.todos = this.todoService.index();
  }

  setEditGroup(){
    this.editGroup = Object.assign({}, this.selected);
  }

  displayGroup(group: Usergroup){
    this.selected = group;
  }

  displayTable(){
    this.selected = null;
  }

  joinGroup(group: Usergroup){
    this.userService.showLoggedInUser().subscribe(
      user => {
        this.currentUser = user;
        if (user.id === group.creator.id){
          console.log("Already in the group as creator");
        }else {
          console.log("Group ID "+group.id);
          this.groupService.joinGroup(group.id).subscribe(
            good => {
              //this.loadGroups();
              // this.updateGroup(good);
              console.log("Success "+good);
              console.log(good);

            },
            err => {
              console.log("Error in joinGroup usergroupComponent "+err);
            }
          )
        }
      },
      err => {
        console.log("Error in JoinGroup "+err);
      }
    )

  }

  openLg(content) {
    this.modalService.open(content, { size: 'lg' });
  }

  addNewGroup(userGroup: Usergroup){
    this.groupService.create(userGroup).subscribe(
      good => {
        console.log(good);
        this.loadGroups();
        this.newGroup = new Usergroup();
      },
      bad =>{
        console.error('addNewGroupComponent.addNewGroup(): error adding group')
        console.error(bad);
      }
    );
  }

}
