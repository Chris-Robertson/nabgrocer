import { Component, OnInit } from '@angular/core';
import {NabGrocerApiService} from "../nab-grocer-api.service";

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent {

  constructor(
    private nabGrocerApi: NabGrocerApiService
  ) { }

  getAllItems() {
    this.nabGrocerApi.getItems();
  }
}
