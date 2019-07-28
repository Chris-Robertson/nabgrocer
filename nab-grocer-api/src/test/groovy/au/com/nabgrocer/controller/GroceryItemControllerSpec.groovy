package au.com.nabgrocer.controller

import au.com.nabgrocer.model.ErrorResponseBody
import au.com.nabgrocer.model.GroceryItem
import au.com.nabgrocer.model.GroceryTag
import au.com.nabgrocer.repository.GroceryItemRepository
import au.com.nabgrocer.repository.GroceryTagRepository
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GroceryItemControllerSpec extends Specification {

    @Autowired
    private GroceryItemRepository itemRepository

    @Autowired
    private GroceryTagRepository tagRepository

    @Autowired
    private MockMvc mockMvc

    private JsonSlurper slurper = new JsonSlurper()

    def setup() {
        if (itemRepository != null) {
            itemRepository.deleteAll()
        }
        if (tagRepository != null) {
            tagRepository.deleteAll()
        }
    }

    def cleanup() {
        if (itemRepository != null) {
            itemRepository.deleteAll()
        }
        if (tagRepository != null) {
            tagRepository.deleteAll()
        }
    }

    def "should return 200 and grocery item for valid get item by itemId request"() {
        given:
        itemRepository.save(new GroceryItem(itemName: "apples"))

        when:
        def response = mockMvc.perform(get("/v1/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()

        then:
        response.status == 200
        def item = slurper.parseText(response.getContentAsString()) as GroceryItem
        item.itemName == 'apples'
    }

    def "should return 200 and all grocery items with tag for valid get items by tagId request"() {
        given:
        def newTag = new GroceryTag(tagName: "confectionery")
        tagRepository.save(newTag)
        itemRepository.save(new GroceryItem(itemName: "smarties", itemTags: [newTag]))
        itemRepository.save(new GroceryItem(itemName: "milky way", itemTags: [newTag]))

        when:
        def response = mockMvc.perform(get("/v1/items?tagId=${newTag.tagId}")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()

        then:
        response.status == 200
        def item = slurper.parseText(response.getContentAsString()) as List<GroceryItem>
        item[0].itemName == 'smarties'
        item[0].itemTags[0].tagName == 'confectionery'
        item[1].itemName == 'milky way'
        item[1].itemTags[0].tagName == 'confectionery'
    }

    def "should return 201 CREATED and grocery item for valid post request"() {
        given:
        itemRepository.save(new GroceryItem(itemName: 'chocolate'))

        when:
        def response = mockMvc.perform(post("/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemName\":\"bananas\"}"))
                .andReturn()
                .getResponse()

        then:
        response.status == 201
        def item = slurper.parseText(response.getContentAsString()) as GroceryItem
        item.itemName == 'bananas'
        itemRepository.findByItemName('chocolate') != null
    }

    def "should return 200 and update existing item in database for valid put request"() {
        given:
        def savedItem = itemRepository.save(new GroceryItem(itemName: 'cabbage'))

        when:
        def response = mockMvc.perform(put("/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemId\":${savedItem.itemId}," +
                        "\"itemName\":\"cabbage\"," +
                        "\"itemTags\":[{\"tagName\":\"vegetable\"}]}"))
                .andReturn()
                .getResponse()

        then:
        response.status == 200
        def item = slurper.parseText(response.getContentAsString()) as GroceryItem
        item.itemName == 'cabbage'
        item.itemTags[0].tagName == 'vegetable'
        def dbItem = itemRepository.findByItemName('cabbage')
        def dbItemTags = dbItem.getItemTags()
        dbItemTags[0].tagName == 'vegetable'
    }

    def "should return 400 BAD_REQUEST when PUT request has no itemId"() {
        when:
        def response = mockMvc.perform(put("/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemName\":\"cabbage\",\"itemTags\":[{\"tagName\":\"vegetable\"}]}"))
                .andReturn()
                .getResponse()

        then:
        response.status == 400
        def body = slurper.parseText(response.getContentAsString()) as ErrorResponseBody
        body.httpStatus == "400 BAD_REQUEST"
        body.errorMessage == "Update item PUT request requires non-zero itemId in payload object"
    }

    def "should return 400 BAD_REQUEST when PUT request has itemId of 0"() {
        when:
        def response = mockMvc.perform(put("/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemId\":0,\"itemName\":\"cabbage\",\"itemTags\":[{\"tagName\":\"vegetable\"}]}"))
                .andReturn()
                .getResponse()

        then:
        response.getStatus() == 400
        def body = slurper.parseText(response.getContentAsString()) as ErrorResponseBody
        body.httpStatus == "400 BAD_REQUEST"
        body.errorMessage == "Update item PUT request requires non-zero itemId in payload object"
    }

    def "should return 204 NO_CONTENT and delete item from database with valid delete request"() {
        given:
        def savedItem = itemRepository.save(new GroceryItem(itemName: 'soup'))

        when:
        def response = mockMvc.perform(delete ("/v1/items/${savedItem.itemId}")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()

        then:
        response.status == 204
        itemRepository.findByItemId(savedItem.itemId) == null
        itemRepository.findByItemName(savedItem.itemName) == null
    }

    def "should return 404 NOT_FOUND for DELETE request when item does not exist in database"() {
        given:
        itemRepository.deleteAll()

        when:
        def response = mockMvc.perform(delete ("/v1/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()

        then:
        response.status == 404
        def body = slurper.parseText(response.getContentAsString()) as ErrorResponseBody
        body.httpStatus == "404 NOT_FOUND"
        body.errorMessage == "GroceryItem with provided itemId does not exist in database."
    }
}
