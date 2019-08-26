import {ItemTag} from "./item-tag";

export class Item {
  constructor(
    public itemName: string,
    public itemId?: number,
    public itemTags?: ItemTag[]
  ) {}

  public getTagsString(): string {
    if (this.itemTags.length > 0) {
      return this.itemTags.map(tag => tag.tagName).join(",");
    }
    return "";
  }
}
