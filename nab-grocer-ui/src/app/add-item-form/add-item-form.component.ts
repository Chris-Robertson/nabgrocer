import {Component} from '@angular/core';
import {ItemsService} from "../items.service";
import {Item} from "../item";
import {FormBuilder} from '@angular/forms';
import {ItemTag} from "../item-tag";

@Component({
  selector: 'app-add-item-form',
  templateUrl: './add-item-form.component.html',
  styleUrls: ['./add-item-form.component.css']
})
export class AddItemFormComponent {

  addItemForm;
  itemAdded: Item;

  constructor(
    private nabGrocerApi: ItemsService,
    private formBuilder: FormBuilder
  ) {
    this.addItemForm = this.formBuilder.group({
      itemName: "",
      itemTags: []
    })
  }

  onSubmit(addItemFormData) {

    // map comma separated tags into list of tag objects
    if (addItemFormData.itemTags) {
      addItemFormData.itemTags = addItemFormData.itemTags.split(',').map(rawTag => {
        return new ItemTag(rawTag.toString());
      });
    }

    this.nabGrocerApi.addItem(addItemFormData).subscribe(item => {
      this.itemAdded = item;
      this.nabGrocerApi.getItems();
    });

    this.addItemForm.reset();
  }
}
