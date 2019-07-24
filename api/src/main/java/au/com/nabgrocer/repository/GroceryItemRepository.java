package au.com.nabgrocer.repository;

import au.com.nabgrocer.model.GroceryItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository extends CrudRepository<GroceryItem, Long> {

    GroceryItem findByName(String name);

    GroceryItem save(GroceryItem groceryItem);

}
