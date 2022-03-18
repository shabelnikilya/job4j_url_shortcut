package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Site;

import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Integer> {

    Optional<Site> findByName(String name);

    Optional<Site> findByLogin(String login);
}
