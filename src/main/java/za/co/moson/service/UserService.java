package za.co.moson.service;

import za.co.moson.models.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    User update(User user);

    void delete(User user);

    Optional<User> findUserByRefNumber(String refNumber);

    Optional<User> findUserByID(Long id);
}
