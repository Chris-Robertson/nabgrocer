package au.com.nabgrocer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*

@AutoConfigureMockMvc
@WebMvcTest
class NabGrocerControllerSpec extends Specification {

    @Autowired
	private MockMvc mockMvc

	def "should return 200 from get item endpoint"() {
		expect: "Status is 200 and the response is 'Hello world!'"
		mockMvc.perform(get("/item?id=1"))
				.andExpect(status().isOk())
				.andReturn()
				.response
				.contentAsString == "{\"id\":\"1\",\"name\":null}"
	}

}
