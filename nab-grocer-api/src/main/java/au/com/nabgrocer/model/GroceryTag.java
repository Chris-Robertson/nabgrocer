package au.com.nabgrocer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class GroceryTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long tagId;

    @Column(unique = true)
    private String tagName;

    @JsonIgnore
    @ManyToMany(mappedBy = "itemTags")
    private Set<GroceryItem> itemsWithTag;

    public Long getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagId(final Long tagId) {
        this.tagId = tagId;
    }

    public void setTagName(final String name) {
        this.tagName = name;
    }

    public Set<GroceryItem> getItemsWithTag() {
        return itemsWithTag;
    }

    public void setItemsWithTag(final Set<GroceryItem> itemsWithTag) {
        this.itemsWithTag = itemsWithTag;
    }

    @Override
    public String toString() {
        return "GroceryTag{"
                + "tagId=" + tagId
                + ", tagName='" + tagName + '\''
                + '}';
    }
}
