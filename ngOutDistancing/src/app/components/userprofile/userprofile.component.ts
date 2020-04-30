import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {


  currentUser = null;

  constructor(private userService: UserService,private currentRoute: ActivatedRoute, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.reload();
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
    // this.userService.index().subscribe(
    //   data => {
    //     this.users = data;
    //   },
    //   bad => {
    //     //this.router.navigateByUrl('/login')
    //     console.error('TodoListComponent.reload(): error retrieving todo list');
    //     console.error(bad);

    //   }
    // );
  }

}
