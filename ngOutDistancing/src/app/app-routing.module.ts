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


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'nonUserLanding' },
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'eventDetail', component: EventDetailComponent},
  { path: 'nonUserLanding', component: NonUserLandingComponent},
  { path: 'userLanding', component: UserLandingComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'admin', component: AdminComponent},
  { path: '**', component: NotFoundComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash:true}) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
