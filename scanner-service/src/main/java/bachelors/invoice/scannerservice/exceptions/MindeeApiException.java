package bachelors.invoice.scannerservice.exceptions;

public class MindeeApiException {

    private String message;

    public MindeeApiException(String message, Throwable cause) {
        this.message = message;
    }
}
