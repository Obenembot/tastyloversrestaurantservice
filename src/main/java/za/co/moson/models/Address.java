package za.co.moson.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    
    private Double latitude;  // Geolocation
    private Double longitude; // Geolocation
}
