package za.co.moson.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuException extends RuntimeException {
    private final int status;

    public MenuException(String message, int status) {
        super(message);
        this.status = status;
    }
}
