package au.com.nabgrocer.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(unique = true)
    private String itemName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<GroceryTag> itemTags;

    public Long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemId(final Long itemId) {
        this.itemId = itemId;
    }

    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    public Set<GroceryTag> getItemTags() {
        return itemTags;
    }

    public void setItemTags(final Set<GroceryTag> itemTags) {
        this.itemTags = itemTags;
    }

    @Override
    public String toString() {
        return "GroceryItem{"
                + "itemId=" + itemId
                + ", itemName='" + itemName + '\''
                + ", itemTags=" + itemTags
                + '}';
    }
}
