import { Component, OnInit } from '@angular/core';
import {ItemsService} from "../items.service";

@Component({
  selector: 'app-list-items',
  templateUrl: './list-items.component.html',
  styleUrls: ['./list-items.component.css']
})
export class ListItemsComponent {

  constructor(
    private nabGrocerApi: ItemsService
  ) { }

  getAllItems() {
    this.nabGrocerApi.getItems();
  }
}
