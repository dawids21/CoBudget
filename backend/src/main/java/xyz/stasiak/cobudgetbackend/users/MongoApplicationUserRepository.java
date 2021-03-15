package xyz.stasiak.cobudgetbackend.users;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface MongoApplicationUserRepository extends ApplicationUserRepository, MongoRepository<ApplicationUser, String> {

    @Override
    Optional<ApplicationUser> findById(String id);

    @Override
    Optional<ApplicationUser> findByEmail(String email);

    @Override
    boolean existsByEmail(String email);
}
