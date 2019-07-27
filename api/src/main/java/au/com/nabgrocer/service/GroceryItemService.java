package au.com.nabgrocer.service;

import au.com.nabgrocer.model.GroceryItem;
import au.com.nabgrocer.repository.GroceryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface GroceryItemService {

    GroceryItem retrieveGroceryItemByName(String name);

    GroceryItem insertGroceryItem(GroceryItem groceryItem);

    GroceryItem updateGroceryItem(GroceryItem groceryItem);

    GroceryItem deleteGroceryItem(GroceryItem groceryItem);

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
        public GroceryItem retrieveGroceryItemByName(final String name) {
            GroceryItem retrievedGroceryItem = groceryItemRepository.findByName(name);
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

        /**
         * Not yet implemented.
         */
        @Override
        public GroceryItem deleteGroceryItem(final GroceryItem groceryItemToDelete) {
            return groceryItemToDelete;
        }
    }
}
