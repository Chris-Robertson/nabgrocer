package au.com.nabgrocer.controller;

import au.com.nabgrocer.exception.GroceryTagAlreadyExistsException;
import au.com.nabgrocer.exception.GroceryTagNotFoundException;
import au.com.nabgrocer.model.GroceryTag;
import au.com.nabgrocer.model.GroceryTagDto;
import au.com.nabgrocer.service.GroceryTagService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroceryTagController {

    private static final Logger LOG = LoggerFactory.getLogger(GroceryTagController.class);

    private final ModelMapper modelMapper = new ModelMapper();

    private final GroceryTagService groceryTagService;

    @Autowired
    public GroceryTagController(final GroceryTagService groceryTagService) {
        this.groceryTagService = groceryTagService;
    }

    @GetMapping("/v1/tags/{tagId}")
    public GroceryTag getItem(final @PathVariable("tagId") long tagId) {
        LOG.debug("'Get tag by id request received' tag_id='{}'", tagId);

        return groceryTagService.getGroceryTagById(tagId);
    }

    @PostMapping("/v1/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public GroceryTag createItem(final @RequestBody GroceryTagDto groceryTagDto)
            throws GroceryTagAlreadyExistsException {

        LOG.debug("'Create tag request received' new_tag='{}'", groceryTagDto);

        final GroceryTag groceryTagToCreate = mapToEntity(groceryTagDto);

        return groceryTagService.insertGroceryTag(groceryTagToCreate);
    }

    @PutMapping("/v1/tags")
    public GroceryTag updateItem(final @RequestBody GroceryTagDto groceryTagDto) {
        LOG.debug("'Update tag request received' tag_update='{}'", groceryTagDto);

        final GroceryTag groceryTagUpdate = mapToEntity(groceryTagDto);

        if (groceryTagUpdate.getTagId() == 0) {
            throw new IllegalArgumentException("Update tag PUT request requires non-zero tagId "
                    + "in payload object");
        }

        return groceryTagService.updateGroceryTag(groceryTagUpdate);
    }

    @DeleteMapping("/v1/tags/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(final @PathVariable int tagId) throws GroceryTagNotFoundException {
        LOG.debug("'Delete tag request received' tag_id='{}'", tagId);

        groceryTagService.deleteGroceryTag(tagId);
    }

    private GroceryTag mapToEntity(final GroceryTagDto groceryTagDto) {
        return modelMapper.map(groceryTagDto, GroceryTag.class);
    }
}
