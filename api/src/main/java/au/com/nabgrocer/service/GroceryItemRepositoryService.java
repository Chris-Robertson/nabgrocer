package au.com.nabgrocer.service;

import au.com.nabgrocer.repository.GroceryItemRepository;
import au.com.nabgrocer.model.GroceryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface GroceryItemRepositoryService {

    GroceryItem retrieveGroceryItemByName(String name);
    GroceryItem insertGroceryItem(GroceryItem groceryItem);

    @Service
    class GroceryItemRepositoryServiceImpl implements GroceryItemRepositoryService {

        private static final Logger LOG = LoggerFactory.getLogger(GroceryItemRepositoryServiceImpl.class);

        private GroceryItemRepository groceryItemRepository;

        @Autowired
        public GroceryItemRepositoryServiceImpl(final GroceryItemRepository groceryItemRepository) {
            this.groceryItemRepository = groceryItemRepository;
        }

        @Override
        public GroceryItem retrieveGroceryItemByName(final String name) {
            GroceryItem retrievedGroceryItem = groceryItemRepository.findByName(name);
            LOG.debug("'Retrieved grocery item from database' retrieved_grocery_item='{}'", retrievedGroceryItem);
            return retrievedGroceryItem;
        }

        @Override
        public GroceryItem insertGroceryItem(final GroceryItem groceryItemToSave) {
            GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItemToSave);
            LOG.debug("'Saved grocery item to database' saved_grocery_item='{}'", savedGroceryItem);
            return savedGroceryItem;
        }
    }
}
