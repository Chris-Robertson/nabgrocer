package au.com.nabgrocer.service;

import au.com.nabgrocer.exception.GroceryTagAlreadyExistsException;
import au.com.nabgrocer.exception.GroceryTagNotFoundException;
import au.com.nabgrocer.model.GroceryTag;
import au.com.nabgrocer.repository.GroceryTagRepository;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GroceryTagService {

    private static final Logger LOG = LoggerFactory.getLogger(GroceryTagService.class);

    private final GroceryTagRepository groceryTagRepository;

    @Autowired
    public GroceryTagService(final GroceryTagRepository groceryTagRepository) {
        this.groceryTagRepository = groceryTagRepository;
    }

    public GroceryTag getGroceryTagById(final long tagId) {
        final GroceryTag retrievedGroceryTag = groceryTagRepository.findByTagId(tagId);

        LOG.debug("'Retrieved grocery tag from database' "
                + "retrieved_grocery_tag='{}'", retrievedGroceryTag);

        return retrievedGroceryTag;
    }

    public GroceryTag insertGroceryTag(final GroceryTag groceryTagToSave)
            throws GroceryTagAlreadyExistsException {

        if (groceryTagRepository.findByTagName(groceryTagToSave.getTagName()) != null) {
            throw new GroceryTagAlreadyExistsException("A GroceryTag with the name \'"
                    + groceryTagToSave.getTagName() + "\' already exists in the database.");
        }

        final GroceryTag savedGroceryTag = groceryTagRepository.save(groceryTagToSave);

        LOG.debug("'Saved grocery tag to database' saved_grocery_tag='{}'", savedGroceryTag);

        return savedGroceryTag;
    }

    public GroceryTag updateGroceryTag(final GroceryTag groceryTagUpdate) {
        final GroceryTag updatedGroceryTag = groceryTagRepository.save(groceryTagUpdate);

        LOG.debug("'Updated grocery tag in database' updated_grocery_tag='{}'", updatedGroceryTag);

        return updatedGroceryTag;
    }

    public void deleteGroceryTag(final long tagId) throws GroceryTagNotFoundException {
        if (groceryTagRepository.existsById(tagId)) {
            groceryTagRepository.deleteById(tagId);

        } else {
            throw new GroceryTagNotFoundException("GroceryTag with provided tagId does not "
                    + "exist in database.");
        }
    }
}
