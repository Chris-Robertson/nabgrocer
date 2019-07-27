package au.com.nabgrocer.controller;

import au.com.nabgrocer.model.GroceryItem;
import au.com.nabgrocer.model.GroceryItemDto;
import au.com.nabgrocer.service.GroceryItemService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NabGrocerController {

    private static final Logger LOG = LoggerFactory.getLogger(NabGrocerController.class);

    private final ModelMapper modelMapper = new ModelMapper();

    private final GroceryItemService groceryItemService;

    @Autowired
    public NabGrocerController(final GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @GetMapping("v1/items")
    public GroceryItem getItem(final @RequestParam("name") String name) {
        LOG.debug("'Get item by name request received' name_param='{}'", name);
        return groceryItemService.retrieveGroceryItemByName(name);
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public GroceryItem createItem(final @RequestBody GroceryItemDto groceryItemDto) {
        LOG.debug("'Create item request received' new_item='{}'", groceryItemDto);
        final GroceryItem groceryItemToCreate = mapToEntity(groceryItemDto);
        return groceryItemService.insertGroceryItem(groceryItemToCreate);
    }

    @PutMapping("/items")
    public GroceryItem updateItem(final @RequestBody GroceryItemDto groceryItemDto) {
        LOG.debug("'Update item request received' item_to_update='{}'", groceryItemDto);
        final GroceryItem groceryItemToUpdate = mapToEntity(groceryItemDto);
        return groceryItemService.updateGroceryItem(groceryItemToUpdate);
    }

    private GroceryItem mapToEntity(final GroceryItemDto groceryItemDto) {
        return modelMapper.map(groceryItemDto, GroceryItem.class);
    }
}
