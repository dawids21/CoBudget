package xyz.stasiak.cobudgetbackend.users;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);
}
