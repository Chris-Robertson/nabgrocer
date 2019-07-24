package au.com.nabgrocer.service;

import au.com.nabgrocer.repository.GroceryItemRepository;
import au.com.nabgrocer.model.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface GroceryItemRepositoryService {

    GroceryItem retrieveGroceryItemByName(String name);

    @Service
    class GroceryItemRepositoryServiceImpl implements GroceryItemRepositoryService {

        private GroceryItemRepository groceryItemRepository;

        @Autowired
        public GroceryItemRepositoryServiceImpl(final GroceryItemRepository groceryItemRepository) {
            this.groceryItemRepository = groceryItemRepository;
        }

        @Override
        public GroceryItem retrieveGroceryItemByName(final String name) {
            return groceryItemRepository.findByName(name);
        }
    }
}

