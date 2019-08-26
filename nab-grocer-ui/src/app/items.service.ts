import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Item} from "./item";
import { Observable } from 'rxjs';
import {ItemTag} from "./item-tag";

@Injectable({
  providedIn: 'root'
})
export class ItemsService {

  nabGrocerItemsUrl = "http://localhost:8080/v1/items";

  itemList: Item[] = [];

  constructor(private http: HttpClient) { }

  getItems(): void {
    this.http.get<Item[]>(this.nabGrocerItemsUrl)
      .subscribe(result => result.map(item => {
        this.itemList.push(new Item(item.itemName, item.itemId, item.itemTags))
      }));
  }

  getItemById(itemId: number): Observable<Item> {
    return this.http.get<Item>(`${this.nabGrocerItemsUrl}/${itemId}`);
  }

  deleteItemById(itemId: number): void {
    this.http.delete<Item>(`${this.nabGrocerItemsUrl}/${itemId}`).subscribe();
  }

  addItem(item: Item): Observable<Item> {
    return this.http.post<Item>(this.nabGrocerItemsUrl, item);
  }
}
