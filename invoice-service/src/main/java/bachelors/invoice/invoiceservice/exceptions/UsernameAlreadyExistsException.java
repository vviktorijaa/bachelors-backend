package bachelors.invoice.invoiceservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UsernameAlreadyExistsException extends RuntimeException {
    /**
     * This method informs that there is already User with such Username
     */
    public UsernameAlreadyExistsException() {
        super("User with entered username already exists");
    }
}