import { Component } from '@angular/core';
import { Input } from '@angular/core';
import {Item} from "../item";
import {ItemsService} from "../items.service";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent {
  @Input() item: Item;
  deleted = false;

  constructor(
    private itemsService: ItemsService
  ) {}

  deleteItem() {
    this.itemsService.deleteItemById(this.item.itemId);
    this.deleted = true
  }

}
