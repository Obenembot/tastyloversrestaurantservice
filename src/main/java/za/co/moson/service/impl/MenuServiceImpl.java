package za.co.moson.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.moson.exceptions.MenuException;
import za.co.moson.exceptions.UserException;
import za.co.moson.models.Menu;
import za.co.moson.repos.MenuRepository;
import za.co.moson.service.MenuService;
import za.co.moson.utils.BuilderUtil;
import za.co.moson.utils.CheckUtil;
import za.co.moson.utils.Constants;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    private final MenuRepository menuRepository;
    private final CheckUtil checkUtil;
    private final BuilderUtil builderUtil;

    public MenuServiceImpl(final MenuRepository menuRepository,
                           final CheckUtil checkUtil,
                           final BuilderUtil builderUtil) {
        this.menuRepository = menuRepository;
        this.checkUtil = checkUtil;
        this.builderUtil = builderUtil;
    }

    /**
     * @param menu
     * @return
     * @throws UserException
     */
    @Override
    public Menu create(Menu menu, String zoneId) throws MenuException {
        logger.info("[{}] [{}] [create()] create menu {}", Constants.SERVICE_NAME, Constants.INFO, menu);
        if (!this.checkUtil.isEmpty(menu.getId())) {
            throw new MenuException("Invalid Menu Object", 400);
        }
        if (this.checkUtil.isEmpty(menu.getRestaurant())) {
            throw new MenuException("Invalid Restaurant Object", 400);
        }
        this.builderUtil.buildCreate(menu, zoneId);
        return this.menuRepository.save(menu);
    }

    /**
     * @param menu
     * @return
     * @throws UserException
     */
    @Override
    public Menu update(Menu menu, String zoneId) throws MenuException {
        logger.info("[{}] [{}] [update()] update menu {}", Constants.SERVICE_NAME, Constants.INFO, menu);
        if (this.checkUtil.isEmpty(menu.getId())) {
            throw new MenuException("Invalid Menu Object", 400);
        }
        if (this.checkUtil.isEmpty(menu.getRestaurant())) {
            throw new MenuException("Invalid Restaurant Object", 400);
        }
        this.builderUtil.buildUpdate(menu, zoneId);
        return this.menuRepository.save(menu);
    }

    /**
     * @param menu
     */
    @Override
    public void delete(Menu menu, String zoneId) {
        logger.info("[{}] [{}] [delete()] delete menu {}", Constants.SERVICE_NAME, Constants.INFO, menu);
        this.builderUtil.buildDelete(menu, zoneId);
        this.menuRepository.save(menu);
    }

    /**
     * @param restaurantId
     * @return
     */
    @Override
    public Page<Menu> findByRestaurantId(Long restaurantId, Pageable pageable) {
        return this.menuRepository.findByRestaurantId(restaurantId, pageable);
    }
}
