package za.co.moson.models;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Address {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    
    private Double latitude;  // Geolocation
    private Double longitude; // Geolocation
}
