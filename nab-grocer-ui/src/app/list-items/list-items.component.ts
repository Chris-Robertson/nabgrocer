import { Component, OnInit } from '@angular/core';
import {NabGrocerApiService} from "../nab-grocer-api.service";

@Component({
  selector: 'app-list-items',
  templateUrl: './list-items.component.html',
  styleUrls: ['./list-items.component.css']
})
export class ListItemsComponent {

  constructor(
    private nabGrocerApi: NabGrocerApiService
  ) { }

  getAllItems() {
    this.nabGrocerApi.getItems();
  }
}
