import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Item} from "./item";

@Injectable({
  providedIn: 'root'
})
export class NabGrocerApiService {

  nabGrocerItemsUrl = "http://localhost:8080/v1/items/";

  itemList: Item[] = [];

  constructor(private http: HttpClient) { }

  getItems(): void {
    console.log("itemList=" + this.itemList);
    this.http.get<Item[]>(this.nabGrocerItemsUrl)
      .subscribe(result => this.itemList = result);
  }
}
