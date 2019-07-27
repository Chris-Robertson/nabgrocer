package au.com.nabgrocer.model;

public class GroceryItemDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroceryItemDto{"
                + "name='" + name + '\''
                + '}';
    }
}
