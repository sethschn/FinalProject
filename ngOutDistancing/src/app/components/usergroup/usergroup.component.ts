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
    console.log("Displaying Group");
    console.log(group);
    this.selected = group;
    console.log(this.selected.users);
  }

  displayTable(){
    this.selected = null;
  }

  isCreator(creator: User){
    if (parseInt(this.authService.getCurrentUserId()) === creator.id){
      console.log("You created this group");
      return true;
    }else {
      console.log("You can't edit this group");
      return false;
    }
  }

  isMember(group: Usergroup){
    console.log("Is member check()");
    let member = false;
    group.users.forEach(user=>{

      console.log(user.username);
      if (parseInt(this.authService.getCurrentUserId()) == user.id){
        console.log("You are a member of this group");
        member = true;
      }
    });
    console.log("End statement");
    return member;
  }

  leaveGroup(group: Usergroup){
    this.groupService.leaveGroup(group.id).subscribe(
      good => {
        //this.selected = null;
        this.loadGroups();
        this.groupService.show(group.id).subscribe(
          group => {
            this.selected = group;
          },
          err => {
            console.log("Error showing updated group");
            console.log(err);
          }
        )
        console.log("Leave group success");
        console.log(good);
      },
      err => {
        console.log("Error leaving group in usergroup component");
        console.log(err);
      }
    )
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
              this.loadGroups();
              this.selected = good;
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
