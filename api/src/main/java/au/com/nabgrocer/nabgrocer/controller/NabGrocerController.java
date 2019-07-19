package au.com.nabgrocer.nabgrocer.controller;

import au.com.nabgrocer.nabgrocer.model.GroceryItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NabGrocerController {

    @GetMapping("/item")
    public GroceryItem item(@RequestParam(value="id") String id,
                            @RequestParam(value="name", required = false) String name) {

        GroceryItem groceryItem = new GroceryItem().setId(id);

        if (name != null) {
            groceryItem.setName(name);
        }

        return groceryItem;
    }

}
