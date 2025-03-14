package za.co.moson.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.moson.exceptions.RestaurantException;
import za.co.moson.models.Restaurant;
import za.co.moson.repos.RestaurantRepository;
import za.co.moson.service.RestaurantService;
import za.co.moson.utils.BuilderUtil;
import za.co.moson.utils.CheckUtil;
import za.co.moson.utils.Constants;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);
    private final RestaurantRepository restaurantRepository;
    private final BuilderUtil builderUtil;
    private final CheckUtil checkUtil;

    public RestaurantServiceImpl(final RestaurantRepository restaurantRepository,
                                 final BuilderUtil builderUtil,
                                 final CheckUtil checkUtil) {
        this.restaurantRepository = restaurantRepository;
        this.builderUtil = builderUtil;
        this.checkUtil = checkUtil;
    }

    /**
     * @param restaurant
     * @return
     */
    @Override
    public Restaurant create(Restaurant restaurant) {
        logger.info("[{}] [{}] [create()] create restaurant {}", Constants.SERVICE_NAME, Constants.INFO, restaurant);
        this.builderUtil.buildCreate(restaurant, restaurant.getUser().getZoneId());
        return this.restaurantRepository.save(restaurant);
    }

    /**
     * @param restaurant
     * @return
     */
    @Override
    public Restaurant update(Restaurant restaurant) throws RestaurantException {
        logger.info("[{}] [{}] [update()] update restaurant {}", Constants.SERVICE_NAME, Constants.INFO, restaurant);
        if (this.checkUtil.isEmpty(restaurant) || this.checkUtil.isEmpty(restaurant.getId())) {
            throw new RestaurantException("Invalid Restaurant", 400);
        }

        if (this.checkUtil.isEmpty(restaurant.getUser())) {
            throw new RestaurantException("Invalid User Object", 400);
        }
        this.builderUtil.buildUpdate(restaurant, restaurant.getUser().getZoneId());
        return this.restaurantRepository.save(restaurant);
    }

    /**
     * @param restaurant
     * @return
     */
    @Override
    public Restaurant delete(Restaurant restaurant) {
        logger.info("[{}] [{}] [delete()] delete restaurant {}", Constants.SERVICE_NAME, Constants.INFO, restaurant);

        this.builderUtil.buildDelete(restaurant, restaurant.getUser().getZoneId());
        this.restaurantRepository.save(restaurant);
        return null;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Page<Restaurant> findAllByNameLike(String name, Pageable pageable) {
        logger.info("[{}] [{}] [findAllByNameLike()] find restaurant by name {}", Constants.SERVICE_NAME, Constants.INFO, name);
        return this.restaurantRepository.findAllByNameLike(name, pageable);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public Page<Restaurant> findAllByUserId(String userId, Pageable pageable) {
        logger.info("[{}] [{}] [findAllByRefNumber()] find restaurant by userId {}", Constants.SERVICE_NAME, Constants.INFO, userId);
        return this.restaurantRepository.findAllByUserId(userId, pageable);
    }
}
