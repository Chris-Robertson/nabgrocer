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

/**
 * RESTful CRUD endpoints for GroceryItems.
 */
@RestController
public class GroceryItemController {

    private static final Logger LOG = LoggerFactory.getLogger(GroceryItemController.class);

    private final ModelMapper modelMapper = new ModelMapper();

    private final GroceryItemService groceryItemService;

    @Autowired
    public GroceryItemController(final GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    /**
     * Retrieve an existing GroceryItem by its itemId.
     *
     * @param itemId The itemId to lookup
     * @return the Grocery item retrieved from the database
     */
    @GetMapping("/v1/items/{itemId}")
    public GroceryItem getItem(final @PathVariable("itemId") long itemId) {
        LOG.debug("'Get item by id request received' item_id='{}'", itemId);
        return groceryItemService.getGroceryItemById(itemId);
    }

    /**
     * Retrieve all GroceryItems that have a given tagId in their ItemTags.
     *
     * @param tagId The tagId to search by
     * @return A list of GroceryItems containing given tagId
     * @throws GroceryTagNotFoundException if tagId does not exist in database
     */
    @GetMapping("/v1/items")
    public List<GroceryItem> getItemsByTagId(final @RequestParam("tagId") long tagId)
            throws GroceryTagNotFoundException {

        LOG.debug("'Get items by tagId request received' tag_id='{}'", tagId);

        return groceryItemService.getGroceryItemsByTag(tagId);
    }

    /**
     * Create a new GroceryItem and save it to the database.
     *
     * @param groceryItemDto GroceryItem to create
     * @return The created GroceryItem
     */
    @PostMapping("/v1/items")
    @ResponseStatus(HttpStatus.CREATED)
    public GroceryItem createItem(final @RequestBody GroceryItemDto groceryItemDto) {
        LOG.debug("'Create item request received' new_item='{}'", groceryItemDto);
        final GroceryItem groceryItemToCreate = mapToEntity(groceryItemDto);
        return groceryItemService.insertGroceryItem(groceryItemToCreate);
    }

    /**
     * Update an existing GroceryItem in the database.
     *
     * @param groceryItemDto The GroceryItem with updated fields. Must contain itemId.
     * @return The complete updated GroceryItem
     * @throws IllegalArgumentException if request does not contain itemId
     */
    @PutMapping("/v1/items")
    public GroceryItem updateItem(final @RequestBody GroceryItemDto groceryItemDto)
            throws IllegalArgumentException {
        LOG.debug("'Update item request received' item_update='{}'", groceryItemDto);
        final GroceryItem groceryItemUpdate = mapToEntity(groceryItemDto);

        if (groceryItemUpdate.getItemId() == 0) {
            throw new IllegalArgumentException("Update item PUT request requires non-zero itemId "
                    + "in payload object");
        }

        return groceryItemService.updateGroceryItem(groceryItemUpdate);
    }

    /**
     * Delete an existing GroceryItem from the database.
     * @param itemId The itemId of the GroceryItem to be deleted
     * @throws GroceryItemNotFoundException if no GroceryItem exits for given itemId
     */
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
