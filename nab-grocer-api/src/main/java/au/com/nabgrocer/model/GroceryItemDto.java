package au.com.nabgrocer.model;

import java.util.Set;

public class GroceryItemDto {

    private long itemId;

    private String itemName;

    private Set<GroceryTagDto> itemTags;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(final long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    public Set<GroceryTagDto> getItemTags() {
        return itemTags;
    }

    public void setItemTags(final Set<GroceryTagDto> itemTags) {
        this.itemTags = itemTags;
    }

    @Override
    public String toString() {
        return "GroceryItemDto{"
                + "itemId=" + itemId
                + ", itemName='" + itemName + '\''
                + ", itemTags=" + itemTags
                + '}';
    }
}
