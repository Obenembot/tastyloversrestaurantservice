package za.co.moson.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import za.co.moson.exceptions.MenuException;
import za.co.moson.models.Menu;

public interface MenuService {

    Menu create(Menu menu) throws MenuException;

    Menu update(Menu menu) throws MenuException;

    void delete(Menu menu);

    Page<Menu> findByRestaurantId(Long restaurantId, Pageable pageable);

    Menu update(MultipartFile multipartFile, Long menuId) throws MenuException;
}
