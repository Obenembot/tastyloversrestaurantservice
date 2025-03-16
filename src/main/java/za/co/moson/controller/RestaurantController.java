package za.co.moson.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.co.moson.exceptions.RestaurantException;
import za.co.moson.models.Restaurant;
import za.co.moson.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = {"https://tastylovers.vercel.app", "http://localhost:4200/"})
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
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) throws RestaurantException {
        return ResponseEntity.ok(this.restaurantService.update(restaurant));
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

    @PutMapping("/update-image")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestParam(value = "file") MultipartFile multipartFile, @RequestParam Long restaurantId) throws RestaurantException {
        return ResponseEntity.ok(this.restaurantService.update(multipartFile, restaurantId));
    }
}
