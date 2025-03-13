package za.co.moson.service;

import za.co.moson.exceptions.UserException;
import za.co.moson.models.User;

import java.util.Optional;

public interface UserService {

    User create(User user) throws UserException;

    User update(User user) throws UserException;

    void delete(User user);

    Optional<User> findUserByRefNumber(String refNumber);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByID(Long id);
}
