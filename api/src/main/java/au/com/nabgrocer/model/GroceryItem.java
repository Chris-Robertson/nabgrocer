package au.com.nabgrocer.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroceryItem {

    @Id
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
