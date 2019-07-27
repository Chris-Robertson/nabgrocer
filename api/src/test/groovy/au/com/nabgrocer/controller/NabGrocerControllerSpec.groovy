package au.com.nabgrocer.controller

import au.com.nabgrocer.repository.GroceryItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NabGrocerControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private GroceryItemRepository repository

    def "should return 200 and grocery item for valid request"() {
        expect:
        mockMvc.perform(get("/item?name=apples"))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "{\"name\":\"apples\"}"
    }

    def "should insert item to database with valid post request"() {
        expect:
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"pears\"}") )
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "{\"name\":\"pears\"}"
    }

    def "should insert item to database with valid update request when item does not exist"() {
        expect:
        mockMvc.perform(post("/item/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"carrots\"}") )
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "{\"name\":\"carrots\"}"
    }
}
