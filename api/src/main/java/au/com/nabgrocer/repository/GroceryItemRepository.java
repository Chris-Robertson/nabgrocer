package au.com.nabgrocer.repository;

import au.com.nabgrocer.model.GroceryItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository extends PagingAndSortingRepository<GroceryItem, Long> {

    GroceryItem findById(long itemId);

}
