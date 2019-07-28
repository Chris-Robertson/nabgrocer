package au.com.nabgrocer.repository;

import au.com.nabgrocer.model.GroceryItem;
import au.com.nabgrocer.model.GroceryTag;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository extends PagingAndSortingRepository<GroceryItem, Long> {

    GroceryItem findByItemId(long itemId);

    GroceryItem findByItemName(String name);

    List<GroceryItem> findByItemTags(GroceryTag groceryTag);

}
