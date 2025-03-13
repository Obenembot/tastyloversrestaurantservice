package za.co.moson.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.moson.models.Restaurant;
import za.co.moson.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(final RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(this.restaurantService.create(restaurant));
    }

    @PutMapping("/")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(this.restaurantService.create(restaurant));
    }

    @DeleteMapping("/")
    public ResponseEntity<Restaurant> deleteRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(this.restaurantService.delete(restaurant));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Restaurant>> findAllByNameLike(@RequestParam String search, Pageable pageable) {
        return ResponseEntity.ok(this.restaurantService.findAllByNameLike(search, pageable));
    }

    @GetMapping("/user-id")
    public ResponseEntity<Page<Restaurant>> findAllByUserId(@RequestParam String userId, Pageable pageable) {
        return ResponseEntity.ok(this.restaurantService.findAllByUserId(userId, pageable));
    }
}
