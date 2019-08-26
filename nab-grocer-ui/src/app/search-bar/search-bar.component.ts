import { Component, OnInit } from '@angular/core';
import {ItemsService} from "../items.service";

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent {

  constructor(
    private nabGrocerApi: ItemsService
  ) { }

  getAllItems() {
    this.nabGrocerApi.getItems();
  }
}
