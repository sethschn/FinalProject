import { UserprofileComponent } from './components/userprofile/userprofile.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventDetailComponent } from './components/event-detail/event-detail.component';
import { NonUserLandingComponent } from './components/non-user-landing/non-user-landing.component';
import { UserLandingComponent } from './components/user-landing/user-landing.component';
import { AdminComponent } from './components/admin/admin.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { ResourceDetailComponent } from './components/resource-detail/resource-detail.component';
import { LocationDetailComponent } from './components/location-detail/location-detail.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { ModalFormComponent } from './components/modal-form/modal-form.component';




const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'nonUserLanding' },
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent},
  { path: 'profile', component: UserprofileComponent},
  { path: 'contact', component: ContactComponent},
  { path: 'about', component: AboutComponent},
  { path: 'event/:id', component: EventDetailComponent},
  { path: 'activities/:id', component: NonUserLandingComponent},
  { path: 'resources/:id', component: ResourceDetailComponent},
  { path: 'locations/:id', component: LocationDetailComponent},
  { path: 'users/:id', component: UserLandingComponent},
  { path: 'eventDetail', component: EventDetailComponent},
  { path: 'resourceDetail', component: ResourceDetailComponent},
  { path: 'locationDetail', component: LocationDetailComponent},
  { path: 'nonUserLanding', component: NonUserLandingComponent},
  { path: 'userLanding', component: UserLandingComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'admin', component: AdminComponent},
  { path: '**', component: NotFoundComponent},
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash:true}) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
