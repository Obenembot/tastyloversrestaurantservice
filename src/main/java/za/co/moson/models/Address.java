package za.co.moson.models;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Address extends MultiEntity {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    private Double latitude;  // Geolocation
    private Double longitude; // Geolocation
}
