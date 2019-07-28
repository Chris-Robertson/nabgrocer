package au.com.nabgrocer.service;

import au.com.nabgrocer.exception.GroceryItemNotFoundException;
import au.com.nabgrocer.exception.GroceryTagNotFoundException;
import au.com.nabgrocer.model.GroceryItem;
import au.com.nabgrocer.model.GroceryTag;
import au.com.nabgrocer.repository.GroceryItemRepository;
import au.com.nabgrocer.repository.GroceryTagRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GroceryItemService {

    private static final Logger LOG = LoggerFactory.getLogger(GroceryItemService.class);

    private final GroceryItemRepository groceryItemRepository;

    private final GroceryTagRepository groceryTagRepository;

    @Autowired
    public GroceryItemService(final GroceryItemRepository groceryItemRepository,
                              final GroceryTagRepository groceryTagRepository) {

        this.groceryItemRepository = groceryItemRepository;
        this.groceryTagRepository = groceryTagRepository;
    }

    public GroceryItem getGroceryItemById(final long itemId) {
        final GroceryItem retrievedGroceryItem = groceryItemRepository.findByItemId(itemId);
        LOG.debug("'Retrieved grocery item from database' "
                + "retrieved_grocery_item='{}'", retrievedGroceryItem);
        return retrievedGroceryItem;
    }

    public List<GroceryItem> getGroceryItemsByTag(final long tagId)
            throws GroceryTagNotFoundException {

        if (groceryTagRepository.existsById(tagId)) {
            final GroceryTag groceryTag = groceryTagRepository.findByTagId(tagId);
            final List<GroceryItem> retrievedGroceryItems = groceryItemRepository
                    .findByItemTags(groceryTag);

            LOG.debug("'Retrieved grocery items from database by tagId' "
                    + "retrieved_grocery_items='{}'", retrievedGroceryItems);

            return retrievedGroceryItems;

        } else {
            throw new GroceryTagNotFoundException("GroceryTag with provided tagId does not "
                    + "exist in database.");
        }
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
