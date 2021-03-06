package au.com.nabgrocer.model;

import java.util.Set;

public class GroceryTagDto {

    private long tagId;

    private String tagName;

    private Set<GroceryItemDto> itemsWithTag;

    public long getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagId(final long tagId) {
        this.tagId = tagId;
    }

    public void setTagName(final String name) {
        this.tagName = name;
    }

    public Set<GroceryItemDto> getItemsWithTag() {
        return itemsWithTag;
    }

    public void setItemsWithTag(final Set<GroceryItemDto> itemsWithTag) {
        this.itemsWithTag = itemsWithTag;
    }

    @Override
    public String toString() {
        return "GroceryTagDto{"
                + "tagId=" + tagId
                + ", tagName='" + tagName + '\''
                + ", itemsWithTag=" + itemsWithTag
                + '}';
    }
}
