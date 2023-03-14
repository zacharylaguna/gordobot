import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { PreviewComponent } from './preview/preview.component';
import { ExecuteComponent } from './execute/execute.component';
import { SearchPageComponent } from './search-page/search-page.component';
import { NewrecipeComponent } from './newrecipe/newrecipe.component'

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'preview/:id', component: PreviewComponent },
  { path: 'execute/:id1/:id2', component: ExecuteComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'search', component: SearchPageComponent },
  { path: 'newrecipe', component: NewrecipeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }