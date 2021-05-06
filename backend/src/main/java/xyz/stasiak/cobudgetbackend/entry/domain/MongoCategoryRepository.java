package xyz.stasiak.cobudgetbackend.entry.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

interface MongoCategoryRepository extends CategoryRepository, MongoRepository<Category, String> {

}
