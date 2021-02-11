package xyz.stasiak.cobudgetbackend.users;

import org.springframework.data.mongodb.repository.MongoRepository;

interface MongoUserRepository extends MongoRepository<User, String> {

}
