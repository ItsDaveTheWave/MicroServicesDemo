import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { RatingComponent } from './rating/rating.component';
import { FormRatingComponent } from './rating/form-rating/form-rating.component';

const routes: Routes = [
  { path: '', redirectTo: '/rating', pathMatch: 'full' },
  { path: 'rating', component: RatingComponent },
  { path: 'rating/form', component: FormRatingComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    RatingComponent,
    FormRatingComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
