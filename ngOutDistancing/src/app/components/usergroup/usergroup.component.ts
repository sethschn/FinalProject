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
  group = new Usergroup();
  groups: Usergroup[] = [];
  creator: User = null;
  newGroup = null;
  selected = null;

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
        console.log("----------------"+data);
        this.groups = data;
      },
      err => {
        console.error('Error in our loadGroups() method. ' + err);
      }
    );
  }

  displayGroup(group: Usergroup){
    this.selected = group;
  }

  displayTable(){
    this.selected = null;
  }

  joinGroup(group: Usergroup){

  }

  addNewGroup(userGroup: Usergroup){
    this.groupService.create(userGroup).subscribe(
      good => {
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
