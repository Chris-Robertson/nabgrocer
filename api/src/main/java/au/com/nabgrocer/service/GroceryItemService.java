package au.com.nabgrocer.service;

import au.com.nabgrocer.exception.GroceryItemNotFoundException;
import au.com.nabgrocer.model.GroceryItem;
import au.com.nabgrocer.repository.GroceryItemRepository;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GroceryItemService {

    private static final Logger LOG = LoggerFactory
            .getLogger(GroceryItemService.class);

    private final GroceryItemRepository groceryItemRepository;

    @Autowired
    public GroceryItemService(final GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    public GroceryItem getGroceryItemById(final long itemId) {
        final GroceryItem retrievedGroceryItem = groceryItemRepository.findByItemId(itemId);
        LOG.debug("'Retrieved grocery item from database' "
                + "retrieved_grocery_item='{}'", retrievedGroceryItem);
        return retrievedGroceryItem;
    }

    public GroceryItem insertGroceryItem(final GroceryItem groceryItemToSave) {
        final GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItemToSave);
        LOG.debug("'Saved grocery item to database' "
                + "saved_grocery_item='{}'", savedGroceryItem);
        return savedGroceryItem;
    }

    public GroceryItem updateGroceryItem(final GroceryItem groceryItemUpdate) {
        final GroceryItem updatedGroceryItem = groceryItemRepository.save(groceryItemUpdate);
        LOG.debug("'Updated grocery item in database' "
                + "updated_grocery_item='{}'", updatedGroceryItem);
        return updatedGroceryItem;
    }

    public void deleteGroceryItem(final long itemId) throws GroceryItemNotFoundException {
        if (groceryItemRepository.existsById(itemId)) {
            groceryItemRepository.deleteById(itemId);
        } else {
            throw new GroceryItemNotFoundException("GroceryItem with provided itemId does not "
                    + "exist in database.");
        }
    }
}
