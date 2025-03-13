package za.co.moson.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends Exception {
    private final int status;

    public UserException(String message, int status) {
        super(message);
        this.status = status;
    }
}
