package za.co.moson.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.co.moson.models.User;
import za.co.moson.repos.UserRepository;
import za.co.moson.service.UserService;
import za.co.moson.utils.Constants;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    /**
     * @param user
     * @return
     */
    @Override
    public User create(User user) {
        logger.info("[{}] [{}] [create()] create user {}", Constants.SERVICE_NAME, Constants.INFO, user);
        return userRepository.save(user);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User update(User user) {
        logger.info("[{}] [{}] [update()] update user {}", Constants.SERVICE_NAME, Constants.INFO, user);
        return this.userRepository.save(user);
    }

    /**
     * @param user
     */
    @Override
    public void delete(User user) {
        logger.info("[{}] [{}] [delete()] delete user {}", Constants.SERVICE_NAME, Constants.INFO, user);
        this.userRepository.save(user);
    }

    /**
     * @param refNumber
     * @return
     */
    @Override
    public Optional<User> findUserByRefNumber(String refNumber) {
        logger.info("[{}] [{}] [findUserByRefNumber()] find user by RefNumber {}", Constants.SERVICE_NAME, Constants.INFO, refNumber);
        return this.userRepository.findUserByRefNumber(refNumber);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<User> findUserByID(Long id) {
        logger.info("[{}] [{}] [findUserByID()] find user by ID {}", Constants.SERVICE_NAME, Constants.INFO, id);
        return this.userRepository.findById(id);
    }
}
