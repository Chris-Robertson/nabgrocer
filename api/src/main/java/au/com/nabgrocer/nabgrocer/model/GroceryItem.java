package au.com.nabgrocer.nabgrocer.model;

public class GroceryItem {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GroceryItem setId(String id) {
        this.id = id;
        return this;
    }

    public GroceryItem setName(String name) {
        this.name = name;
        return this;
    }

}
