package au.com.nabgrocer.service;

import au.com.nabgrocer.exception.GroceryItemNotFoundException;
import au.com.nabgrocer.model.GroceryItem;
import au.com.nabgrocer.repository.GroceryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface GroceryItemService {

    GroceryItem getGroceryItemById(long itemId);

    GroceryItem insertGroceryItem(GroceryItem groceryItem);

    GroceryItem updateGroceryItem(GroceryItem groceryItem);

    void deleteGroceryItem(long itemId);

    @Service
    class GroceryItemServiceImpl implements GroceryItemService {

        private static final Logger LOG = LoggerFactory
                .getLogger(GroceryItemServiceImpl.class);

        private final GroceryItemRepository groceryItemRepository;

        @Autowired
        public GroceryItemServiceImpl(final GroceryItemRepository groceryItemRepository) {
            this.groceryItemRepository = groceryItemRepository;
        }

        @Override
        public GroceryItem getGroceryItemById(final long itemId) {
            GroceryItem retrievedGroceryItem = groceryItemRepository.findById(itemId);
            LOG.debug("'Retrieved grocery item from database' "
                    + "retrieved_grocery_item='{}'", retrievedGroceryItem);
            return retrievedGroceryItem;
        }

        @Override
        public GroceryItem insertGroceryItem(final GroceryItem groceryItemToSave) {
            GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItemToSave);
            LOG.debug("'Saved grocery item to database' "
                    + "saved_grocery_item='{}'", savedGroceryItem);
            return savedGroceryItem;
        }

        @Override
        public GroceryItem updateGroceryItem(final GroceryItem groceryItemToSave) {
            GroceryItem updatedGroceryItem = groceryItemRepository.save(groceryItemToSave);
            LOG.debug("'Updated grocery item in database' "
                    + "updated_grocery_item='{}'", updatedGroceryItem);
            return updatedGroceryItem;
        }

    public void deleteGroceryItem(final long itemId) throws GroceryItemNotFoundException {
        if (!groceryItemRepository.existsById(itemId)) {
            throw new GroceryItemNotFoundException("GroceryItem with provided itemId does not "
                    + "exist in database.");
        } else {
            groceryItemRepository.deleteById(itemId);
        }
    }
}
