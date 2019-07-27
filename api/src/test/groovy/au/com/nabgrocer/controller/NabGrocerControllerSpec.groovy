package au.com.nabgrocer.controller

import au.com.nabgrocer.model.GroceryItem
import au.com.nabgrocer.repository.GroceryItemRepository
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NabGrocerControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private GroceryItemRepository repository

    def "should return 200 and grocery item for valid get request"() {
        given:
        repository.save(new GroceryItem(name: "apples"))

        expect:
        println repository.findAll()
        mockMvc.perform(get("/v1/items/1"))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "{\"id\":1,\"name\":\"apples\"}"
    }

    def "should insert item to database with valid put request"() {
        expect:
        mockMvc.perform(put("/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"bread\"}") )
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "{\"id\":2,\"name\":\"bread\"}"
    }

    def "should insert item to database with valid update request when item does not exist"() {
        expect:
        mockMvc.perform(post("/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"milk\"}") )
                .andExpect(status().isCreated())
                .andReturn()
                .response
                .contentAsString == "{\"id\":3,\"name\":\"milk\"}"
    }

    def "should delete item from database with valid delete request"() {
        expect:
        mockMvc.perform(delete ("/v1/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }
}
