import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usergroup',
  templateUrl: './usergroup.component.html',
  styleUrls: ['./usergroup.component.css']
})
export class UsergroupComponent implements OnInit {

  constructor( private authService: AuthService,
    private userService: UserService,
    private modalService: NgbModal,
    private router: Router) { }

  ngOnInit(): void {
  }

}
