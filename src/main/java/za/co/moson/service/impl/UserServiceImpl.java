package za.co.moson.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.co.moson.exceptions.UserException;
import za.co.moson.models.User;
import za.co.moson.repos.UserRepository;
import za.co.moson.service.UserService;
import za.co.moson.utils.*;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final GenerateRefNumbers generateRefNumbers;
    private final CheckUtil checkUtil;
    private final EmailValidator emailValidator;
    private final BuilderUtil builderUtil;

    public UserServiceImpl(final UserRepository userRepository,
                           final GenerateRefNumbers generateRefNumbers,
                           final CheckUtil checkUtil,
                           final EmailValidator emailValidator,
                           final BuilderUtil builderUtil) {
        this.userRepository = userRepository;
        this.generateRefNumbers = generateRefNumbers;
        this.checkUtil = checkUtil;
        this.emailValidator = emailValidator;
        this.builderUtil = builderUtil;

    }

    /**
     * @param user
     * @return
     */
    @Override
    public User create(User user) throws UserException {
        logger.info("[{}] [{}] [create()] create user {}", Constants.SERVICE_NAME, Constants.INFO, user);
        if (this.checkUtil.isEmpty(user)) {
            throw new UserException("Invalid User Object", 400);
        }
        if (user.getId() > 0) {
            throw new UserException("Invalid User ID. Cannot create new user with id: " + user.getEmail(), 400);
        } else {
            user.setId(null);
        }
        // Validate Email
        if (!this.emailValidator.isValidEmail(user.getEmail())) {
            throw new UserException("Invalid User Email: " + user.getEmail(), 400);
        }

        // Check if Email already exist for a different user
        Optional<User> userByEmail = this.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new UserException("User Already Exists with Email: " + user.getEmail(), 400);
        }

        //Generate and Validate reference number
        String refNumber = this.generateRefNumbers.generateRef();
        Optional<User> userByRefNumber = this.findUserByRefNumber(refNumber);
        while (userByRefNumber.isPresent()) {
            logger.info("[{}] [{}] [create()] reference number already exists {}", Constants.SERVICE_NAME, Constants.INFO, refNumber);
            refNumber = this.generateRefNumbers.generateRef();
            userByRefNumber = this.findUserByRefNumber(refNumber);
        }
        user.setRefNumber(refNumber);
        this.builderUtil.buildCreate(user, user.getZoneId());
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
     * @param email
     * @return
     */
    @Override
    public Optional<User> findUserByEmail(String email) {
        logger.info("[{}] [{}] [findUserByEmail()] find user by email {}", Constants.SERVICE_NAME, Constants.INFO, email);
        return this.userRepository.findUserByEmail(email);
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
