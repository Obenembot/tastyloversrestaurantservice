package za.co.moson.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import za.co.moson.exceptions.MenuException;
import za.co.moson.models.Menu;

public interface MenuService {

    Menu create(Menu menu, String zoneId) throws MenuException;

    Menu update(Menu menu, String zoneId) throws MenuException;

    void delete(Menu menu, String zoneId);

    Page<Menu> findByRestaurantId(Long restaurantId, Pageable pageable);
}
