import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventDetailComponent } from './components/event-detail/event-detail.component';
import { UserLandingComponent } from './components/user-landing/user-landing.component';
import { NonUserLandingComponent } from './components/non-user-landing/non-user-landing.component';



@NgModule({
  declarations: [
    AppComponent,
    EventDetailComponent,
    UserLandingComponent,
    NonUserLandingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
