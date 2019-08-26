import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule }   from '@angular/forms';

import { AddItemFormComponent } from './add-item-form/add-item-form.component';
import { AppComponent } from './app.component';
import { ItemComponent } from './item/item.component';
import { ListItemsComponent } from './list-items/list-items.component';
import { GetItemByIdComponent } from './get-item-by-id/get-item-by-id.component';

@NgModule({
  declarations: [
    AddItemFormComponent,
    AppComponent,
    ItemComponent,
    ListItemsComponent,
    ListItemsComponent,
    GetItemByIdComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
