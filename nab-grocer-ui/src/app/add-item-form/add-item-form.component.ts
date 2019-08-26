import { Component, OnInit } from '@angular/core';
import {NabGrocerApiService} from "../nab-grocer-api.service";
import {Item} from "../item";
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-add-item-form',
  templateUrl: './add-item-form.component.html',
  styleUrls: ['./add-item-form.component.css']
})
export class AddItemFormComponent {

  addItemForm;
  itemAdded;

  constructor(
    private nabGrocerApi: NabGrocerApiService,
    private formBuilder: FormBuilder
  ) {
    this.addItemForm = this.formBuilder.group({
      itemName: ""
    })
  }

  onSubmit(itemToAdd: Item) {
    this.addItemForm.reset();
    this.nabGrocerApi.addItem(itemToAdd).subscribe(item => this.itemAdded = item);
  }
}
