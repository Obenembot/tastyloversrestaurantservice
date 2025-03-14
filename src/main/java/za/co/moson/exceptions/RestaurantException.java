package za.co.moson.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantException extends Exception {
    private int status;

    public RestaurantException(String message, int status) {
        super(message);
        this.status = status;
    }
}
