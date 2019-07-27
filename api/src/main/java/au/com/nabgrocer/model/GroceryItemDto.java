package au.com.nabgrocer.model;

public class GroceryItemDto {

    private long itemId;

    private String name;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(final long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroceryItemDto{"
                + "itemId=" + itemId
                + ", name='" + name + '\''
                + '}';
    }
}
