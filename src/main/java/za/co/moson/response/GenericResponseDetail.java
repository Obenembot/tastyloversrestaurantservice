package za.co.moson.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenericResponseDetail<T> {
    private String status;
    private String error;
    private String result;
    private T data;
}