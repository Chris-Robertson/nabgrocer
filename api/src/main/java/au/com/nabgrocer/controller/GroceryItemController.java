package au.com.nabgrocer.controller;

import au.com.nabgrocer.exception.GroceryItemNotFoundException;
import au.com.nabgrocer.exception.GroceryTagNotFoundException;
import au.com.nabgrocer.model.GroceryItem;
import au.com.nabgrocer.model.GroceryItemDto;
import au.com.nabgrocer.service.GroceryItemService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroceryItemController {

    private static final Logger LOG = LoggerFactory.getLogger(GroceryItemController.class);

    private final ModelMapper modelMapper = new ModelMapper();

    private final GroceryItemService groceryItemService;

    @Autowired
    public GroceryItemController(final GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @GetMapping("/v1/items/{itemId}")
    public GroceryItem getItem(final @PathVariable("itemId") long itemId) {
        LOG.debug("'Get item by id request received' item_id='{}'", itemId);
        return groceryItemService.getGroceryItemById(itemId);
    }

    @GetMapping("/v1/items")
    public List<GroceryItem> getItemsByTagId(final @RequestParam("tagId") long tagId)
            throws GroceryTagNotFoundException {

        LOG.debug("'Get items by tagId request received' tag_id='{}'", tagId);

        return groceryItemService.getGroceryItemsByTag(tagId);
    }

    @PostMapping("/v1/items")
    @ResponseStatus(HttpStatus.CREATED)
    public GroceryItem createItem(final @RequestBody GroceryItemDto groceryItemDto) {
        LOG.debug("'Create item request received' new_item='{}'", groceryItemDto);
        final GroceryItem groceryItemToCreate = mapToEntity(groceryItemDto);
        return groceryItemService.insertGroceryItem(groceryItemToCreate);
    }

    @PutMapping("/v1/items")
    public GroceryItem updateItem(final @RequestBody GroceryItemDto groceryItemDto) {
        LOG.debug("'Update item request received' item_update='{}'", groceryItemDto);
        final GroceryItem groceryItemUpdate = mapToEntity(groceryItemDto);

        if (groceryItemUpdate.getItemId() == 0) {
            throw new IllegalArgumentException("Update item PUT request requires non-zero itemId "
                    + "in payload object");
        }

        return groceryItemService.updateGroceryItem(groceryItemUpdate);
    }

    @DeleteMapping("/v1/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(final @PathVariable int itemId) throws GroceryItemNotFoundException {
        LOG.debug("'Delete item request received' item_id='{}'", itemId);
        groceryItemService.deleteGroceryItem(itemId);
    }

    private GroceryItem mapToEntity(final GroceryItemDto groceryItemDto) {
        return modelMapper.map(groceryItemDto, GroceryItem.class);
    }
}
