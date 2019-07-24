package au.com.nabgrocer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GroceryItem setId(Long id) {
        this.id = id;
        return this;
    }

    public GroceryItem setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "GroceryItem{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
