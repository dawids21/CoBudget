package xyz.stasiak.cobudgetbackend.users;

import java.util.Optional;

public interface ApplicationUserRepository {

    Optional<ApplicationUser> findById(String id);

    Optional<ApplicationUser> findByEmail(String email);

    ApplicationUser save(ApplicationUser applicationUser);
}
