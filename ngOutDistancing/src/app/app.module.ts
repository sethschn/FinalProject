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
import { EventcommentService } from './services/eventcomment.service';



@NgModule({
  declarations: [
    AppComponent,
    EventDetailComponent,
    UserLandingComponent,
    NonUserLandingComponent,
    NotFoundComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    EventService,
    ResourceService,
    UserService,
    AuthService,
    ActivityService,
    EventcommentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
