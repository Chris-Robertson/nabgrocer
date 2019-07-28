package au.com.nabgrocer.controller

import au.com.nabgrocer.model.ErrorResponseBody
import au.com.nabgrocer.model.GroceryTag
import au.com.nabgrocer.repository.GroceryTagRepository
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GroceryTagControllerSpec extends Specification {

    @Autowired
    private GroceryTagRepository tagRepository

    @Autowired
    private MockMvc mockMvc

    private JsonSlurper slurper = new JsonSlurper()

    def setup() {
        if (tagRepository != null) {
            tagRepository.deleteAll()
        }
    }

    def cleanup() {
        if (tagRepository != null) {
            tagRepository.deleteAll()
        }
    }

    def "should return 200 and grocery tag for valid get tag by tagId request"() {
        given:
        def newTag = tagRepository.save(new GroceryTag(tagName: "fruit"))

        when:
        def response = mockMvc.perform(get("/v1/tags/${newTag.tagId}")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()

        then:
        response.status == 200
        def tag = slurper.parseText(response.getContentAsString()) as GroceryTag
        tag.tagName == 'fruit'
    }

    def "should return 201 CREATED and grocery tag for valid post request"() {
        when:
        def response = mockMvc.perform(post("/v1/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tagName\":\"stationary\"}"))
                .andReturn()
                .getResponse()

        then:
        response.status == 201
        def tag = slurper.parseText(response.getContentAsString()) as GroceryTag
        tag.tagName == 'stationary'
        tagRepository.findByTagName('stationary') != null
    }

    def "should return 400 BAD_REQUEST for POST request when tag name already exists"() {
        given:
        tagRepository.save(new GroceryTag(tagName: "gardening"))

        when:
        def response = mockMvc.perform(post("/v1/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tagName\":\"gardening\"}"))
                .andReturn()
                .getResponse()

        then:
        response.getStatus() == 400
        def body = slurper.parseText(response.getContentAsString()) as ErrorResponseBody
        body.httpStatus == "400 BAD_REQUEST"
        body.errorMessage == "A GroceryTag with the name 'gardening' already exists in the database."
    }

    def "should return 200 and update existing tag in database for valid put request"() {
        given:
        def savedTag = tagRepository.save(new GroceryTag(tagName: 'bakery'))

        when:
        def response = mockMvc.perform(put("/v1/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tagId\":${savedTag.tagId},\"tagName\":\"frozen\"}"))
                .andReturn()
                .getResponse()

        then:
        response.status == 200
        def tag = slurper.parseText(response.getContentAsString()) as GroceryTag
        tag.tagName == 'frozen'
        tagRepository.findByTagId(savedTag.tagId).tagName == ('frozen')
        tagRepository.findByTagName('bakery') == null
    }

    def "should return 400 BAD_REQUEST when PUT request has no tagId"() {
        when:
        def response = mockMvc.perform(put("/v1/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tagName\":\"hardware\"}"))
                .andReturn()
                .getResponse()

        then:
        response.status == 400
        def body = slurper.parseText(response.getContentAsString()) as ErrorResponseBody
        body.httpStatus == "400 BAD_REQUEST"
        body.errorMessage == "Update tag PUT request requires non-zero tagId in payload object"
        tagRepository.findByTagName('hardware') == null
    }

    def "should return 400 BAD_REQUEST when PUT request has tagId of 0"() {
        when:
        def response = mockMvc.perform(put("/v1/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tagId\":0,\"tagName\":\"hardware\"}]}"))
                .andReturn()
                .getResponse()

        then:
        response.getStatus() == 400
        def body = slurper.parseText(response.getContentAsString()) as ErrorResponseBody
        body.httpStatus == "400 BAD_REQUEST"
        body.errorMessage == "Update tag PUT request requires non-zero tagId in payload object"
        tagRepository.findByTagName('hardware') == null
    }

    def "should return 204 NO_CONTENT and delete tag from database with valid delete request"() {
        given:
        def savedTag = tagRepository.save(new GroceryTag(tagName: 'pets'))

        when:
        def response = mockMvc.perform(delete ("/v1/tags/${savedTag.tagId}")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()

        then:
        response.status == 204
        tagRepository.findByTagId(savedTag.tagId) == null
        tagRepository.findByTagName(savedTag.tagName) == null
    }

    def "should return 404 NOT_FOUND for DELETE request when tag does not exist in database"() {
        given:
        tagRepository.deleteAll()

        when:
        def response = mockMvc.perform(delete ("/v1/tags/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()

        then:
        response.status == 404
        def body = slurper.parseText(response.getContentAsString()) as ErrorResponseBody
        body.httpStatus == "404 NOT_FOUND"
        body.errorMessage == "GroceryTag with provided tagId does not exist in database."
    }
}
