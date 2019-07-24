package au.com.nabgrocer.controller;

import au.com.nabgrocer.model.GroceryItem;
import au.com.nabgrocer.service.GroceryItemRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NabGrocerController {

    private Logger log = LoggerFactory.getLogger(NabGrocerController.class);

    private GroceryItemRepositoryService groceryItemRepositoryService;

    @Autowired
    public NabGrocerController(GroceryItemRepositoryService groceryItemRepositoryService) {
        this.groceryItemRepositoryService = groceryItemRepositoryService;
    }

    @GetMapping("/item")
    public GroceryItem item(final @RequestParam(value = "name") String name) {
        log.debug("'Get item by name request received' name_param='{}'", name);
        return groceryItemRepositoryService.retrieveGroceryItemByName(name);
    }
}
