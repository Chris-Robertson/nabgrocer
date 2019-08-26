import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Item} from "./item";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NabGrocerApiService {

  nabGrocerItemsUrl = "http://localhost:8080/v1/items";

  itemList: Item[] = [];

  constructor(private http: HttpClient) { }

  getItems(): void {
    this.http.get<Item[]>(this.nabGrocerItemsUrl)
      .subscribe(result => this.itemList = result);
  }

  getItemById(itemId: number): Observable<Item> {
    return this.http.get<Item>(`${this.nabGrocerItemsUrl}/${itemId}`);
  }

  deleteItemById(itemId: number): Observable<Item> {
    return this.http.delete<Item>(`${this.nabGrocerItemsUrl}/${itemId}`);
  }

  addItem(item: Item): Observable<Item> {
    return this.http.post<Item>(this.nabGrocerItemsUrl, item);
  }
}
