import { Component } from '@angular/core';
import {ItemsService} from "../items.service";
import { FormBuilder } from '@angular/forms';
import {Item} from "../item";

@Component({
  selector: 'app-get-item-by-id',
  templateUrl: './get-item-by-id.component.html',
  styleUrls: ['./get-item-by-id.component.css']
})
export class GetItemByIdComponent {

  getItemByIdForm;
  item: Item;

  constructor(
    private nabGrocerApi: ItemsService,
    private formBuilder: FormBuilder
  ) {
    this.getItemByIdForm = this.formBuilder.group({
      itemId: ""
    })
  }

  onSubmit(formData) {
    this.getItemByIdForm.reset();
    this.nabGrocerApi.getItemById(formData.itemId).subscribe(item => this.item = item);
  }
}
