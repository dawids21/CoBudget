package xyz.stasiak.cobudgetbackend.users;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface MongoUserRepository extends UserRepository, MongoRepository<User, String> {

    @Override
    Optional<User> findById(String id);

    @Override
    Optional<User> findByEmail(String email);
}
