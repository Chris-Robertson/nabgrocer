package au.com.nabgrocer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*

@SpringBootTest
@AutoConfigureMockMvc
class NabGrocerControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

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
}
