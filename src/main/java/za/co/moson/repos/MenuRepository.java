package za.co.moson.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.moson.models.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = "SELECT *FROM menus WHERE  restaurant_id = ?1", nativeQuery = true)
    Page<Menu> findByRestaurantId(Long restaurantId, Pageable pageable);

}
