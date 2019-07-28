package au.com.nabgrocer.config;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import au.com.nabgrocer.exception.GroceryItemNotFoundException;
import au.com.nabgrocer.model.ErrorResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory
            .getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex,
                                                        WebRequest request) {
        LOG.error(ex.getMessage());
        return buildResponse(ex, BAD_REQUEST, request);
    }

    @ExceptionHandler(GroceryItemNotFoundException.class)
    public ResponseEntity<Object> handleGroceryItemNotFound(GroceryItemNotFoundException ex,
                                                            WebRequest request) {
        LOG.error(ex.getMessage());
        return buildResponse(ex, NOT_FOUND, request);
    }

    private ResponseEntity<Object> buildResponse(Exception ex,
                                                 HttpStatus httpStatus,
                                                 WebRequest request) {
        return handleExceptionInternal(ex, buildJsonErrorBody(ex, httpStatus),
                buildResponseHeaders(), httpStatus, request);
    }

    private String buildJsonErrorBody(Exception ex, HttpStatus httpStatus) {
        try {
            return new ObjectMapper().writeValueAsString(new ErrorResponseBody(
                    httpStatus.toString(), ex.getMessage()));
        } catch (JsonProcessingException jsonException) {
            String jsonErrorString = "Could not serialise error response body";
            LOG.error(jsonErrorString, jsonException);
            return jsonErrorString;
        }
    }

    private static HttpHeaders buildResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return headers;
    }
}
