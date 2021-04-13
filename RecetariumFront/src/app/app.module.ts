import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IngredientListComponent } from './ingredient-list/ingredient-list.component';
import { HomeComponent } from './home/home.component';
import { IngredientItemComponent } from './ingredient-item/ingredient-item.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
<<<<<<< HEAD
import { BaseUrlInterceptor } from './interceptors/base-url.interceptor';
=======
import { IngredientFormComponent } from './ingredient-form/ingredient-form.component';
>>>>>>> 9a8394e200dbc6d7e2234dba5fbd81cad39a4e79

@NgModule({
  declarations: [
    AppComponent,
    IngredientListComponent,
    HomeComponent,
    IngredientItemComponent,
    IngredientFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BaseUrlInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
