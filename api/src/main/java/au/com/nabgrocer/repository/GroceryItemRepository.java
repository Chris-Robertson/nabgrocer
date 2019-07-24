package au.com.nabgrocer.repository;

import au.com.nabgrocer.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {

    GroceryItem findByName(String name);

}
