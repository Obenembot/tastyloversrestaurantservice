package za.co.moson.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.co.moson.exceptions.MenuException;
import za.co.moson.exceptions.UserException;
import za.co.moson.models.Menu;
import za.co.moson.repos.MenuRepository;
import za.co.moson.service.MenuService;
import za.co.moson.utils.BuilderUtil;
import za.co.moson.utils.CheckUtil;
import za.co.moson.utils.Constants;

import java.util.Optional;

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
    public Menu create(Menu menu) throws MenuException {
        logger.info("[{}] [{}] [create()] create menu {}", Constants.SERVICE_NAME, Constants.INFO, menu);
        if (!this.checkUtil.isEmpty(menu.getId())) {
            throw new MenuException("Invalid Menu Object", 400);
        }
        if (this.checkUtil.isEmpty(menu.getRestaurant())) {
            throw new MenuException("Invalid Restaurant Object", 400);
        }
        this.builderUtil.buildCreate(menu, menu.getRestaurant().getUser().getZoneId());
        return this.menuRepository.save(menu);
    }

    /**
     * @param menu
     * @return
     * @throws UserException
     */
    @Override
    public Menu update(Menu menu) throws MenuException {
        logger.info("[{}] [{}] [update()] update menu {}", Constants.SERVICE_NAME, Constants.INFO, menu);
        if (this.checkUtil.isEmpty(menu.getId())) {
            throw new MenuException("Invalid Menu Object", 400);
        }
        if (this.checkUtil.isEmpty(menu.getRestaurant())) {
            throw new MenuException("Invalid Restaurant Object", 400);
        }
        this.builderUtil.buildUpdate(menu, menu.getRestaurant().getUser().getZoneId());
        return this.menuRepository.save(menu);
    }

    /**
     * @param menu
     */
    @Override
    public void delete(Menu menu) {
        logger.info("[{}] [{}] [delete()] delete menu {}", Constants.SERVICE_NAME, Constants.INFO, menu);
        this.builderUtil.buildDelete(menu, menu.getRestaurant().getUser().getZoneId());
        this.menuRepository.save(menu);
    }

    /**
     * @param restaurantId
     * @return
     */
    @Override
    public Page<Menu> findByRestaurantId(Long restaurantId, Pageable pageable) {
        logger.info("[{}] [{}] [findByRestaurantId()] find Menu By RestaurantId {}", Constants.SERVICE_NAME, Constants.INFO, restaurantId);
        return this.menuRepository.findByRestaurantId(restaurantId, pageable);
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<Menu> findAllMenus(Pageable pageable) {
        logger.info("[{}] [{}] [findByRestaurantId()] find All Menu By pageable {}", Constants.SERVICE_NAME, Constants.INFO, pageable);
        return this.menuRepository.findAll(pageable);
    }

    /**
     * @param multipartFile
     * @param menuId
     * @return
     */
    @Override
    public Menu update(MultipartFile multipartFile, Long menuId) throws MenuException {
        Optional<Menu> menu = this.menuRepository.findById(menuId);
        if (menu.isPresent()) {
            try {
                menu.get().setFileName(multipartFile.getOriginalFilename());
                menu.get().setFileType(multipartFile.getContentType());
                String getBytes =  new String(multipartFile.getBytes());
//                menu.get().setFileContent(multipartFile.getBytes());
                menu.get().setFileContent(getBytes);
                return this.update(menu.get());
            } catch (Exception e) {
                logger.error("[{}] [{}] [update()] find restaurant by restaurantId {}", Constants.SERVICE_NAME, Constants.ERROR, menuId);
            }
        }
        return null;
    }
}
