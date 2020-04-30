import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventDetailComponent } from './components/event-detail/event-detail.component';
import { UserLandingComponent } from './components/user-landing/user-landing.component';
import { NonUserLandingComponent } from './components/non-user-landing/non-user-landing.component';
import { EventService } from './services/event.service';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AdminComponent } from './components/admin/admin.component';
import { ResourceService } from './services/resource.service';
import { UserService } from './services/user.service';
import { AuthService } from './services/auth.service';
import { ActivityService } from './services/activity.service';
import { EventCommentService } from './services/event-comment.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { UserprofileComponent } from './components/userprofile/userprofile.component';




@NgModule({
  declarations: [
    AppComponent,
    EventDetailComponent,
    UserLandingComponent,
    NonUserLandingComponent,
    NotFoundComponent,
    AdminComponent,
    NavBarComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    UserprofileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [
    EventService,
    ResourceService,
    UserService,
    AuthService,
    ActivityService,
    EventCommentService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
