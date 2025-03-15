package za.co.moson.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import za.co.moson.exceptions.RestaurantException;
import za.co.moson.models.Restaurant;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant) throws RestaurantException;

    Restaurant delete(Restaurant restaurant);

    Page<Restaurant> findAllByNameLike(String name, Pageable pageable);

    Page<Restaurant> findAllByUserId(String userId, Pageable pageable);

    Restaurant update(MultipartFile  multipartFile, Long  restaurantId) throws RestaurantException;
}
