package za.co.moson.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.moson.models.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "SELECT * FROM restaurants WHERE name like %?1%", nativeQuery = true)
    Page<Restaurant> findAllByNameLike(String name, Pageable pageable);

    @Query(value = "SELECT * FROM restaurants WHERE user_id  = ?1", nativeQuery = true)
    Page<Restaurant> findAllByUserId(String refNumber, Pageable pageable);
}
