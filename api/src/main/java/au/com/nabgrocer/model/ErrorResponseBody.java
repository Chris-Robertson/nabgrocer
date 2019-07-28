package au.com.nabgrocer.model;

public class ErrorResponseBody {

    private String httpStatus;

    private String errorMessage;

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(final String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponseBody() {
        // Empty constructor for JSON serialisation.
    }

    public ErrorResponseBody(final String httpStatus, final String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorResponseBody{"
                + "httpStatus='" + httpStatus + '\''
                + ", errorMessage='" + errorMessage + '\''
                + '}';
    }
}
