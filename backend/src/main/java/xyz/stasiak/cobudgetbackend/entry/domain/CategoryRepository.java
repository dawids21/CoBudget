package xyz.stasiak.cobudgetbackend.entry.domain;

interface CategoryRepository {

    Category save(Category toSave);

    Category findAllByUsername(String username);

    void deleteByUsername(String username);
}
