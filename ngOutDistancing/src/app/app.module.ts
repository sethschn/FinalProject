import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { NgbModule, NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { UserprofileComponent } from './components/userprofile/userprofile.component';
import { ResourceDetailComponent } from './components/resource-detail/resource-detail.component';
import { LocationDetailComponent } from './components/location-detail/location-detail.component';
import { AboutComponent } from './components/about/about.component';
import { ContactComponent } from './components/contact/contact.component';
import { ModalFormComponent } from './components/modal-form/modal-form.component';
import { DynamicMarkersComponent } from './components/dynamic-markers/dynamic-markers.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { MatSliderModule } from '@angular/material/slider';
import { ActivityComponent } from './components/activity/activity.component';
import { AgmCoreModule } from '@agm/core';
import { CardDisplayComponent } from './components/card-display/card-display.component';


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
    UserprofileComponent,
    ResourceDetailComponent,
    LocationDetailComponent,
    AboutComponent,
    ContactComponent,
    ModalFormComponent,
    DynamicMarkersComponent,
    ActivityComponent,
    CardDisplayComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    NgbModalModule,
    ReactiveFormsModule,
    AgmCoreModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatSliderModule,

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
