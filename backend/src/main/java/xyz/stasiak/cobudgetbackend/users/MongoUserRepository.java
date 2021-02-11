package xyz.stasiak.cobudgetbackend.users;

import org.springframework.data.mongodb.repository.MongoRepository;

interface MongoUserRepository extends UserRepository, MongoRepository<User, String> {

}
