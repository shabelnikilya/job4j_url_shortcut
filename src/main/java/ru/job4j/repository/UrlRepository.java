package ru.job4j.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.model.Site;
import ru.job4j.model.Url;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {

    Url findByLink(String link);

    Optional<Url> findByUniqueCode(String uniqueCode);

    Iterable<Url> findBySite(Site site);

    @Transactional
    @Modifying
    @Query("UPDATE Url u SET u.statistic = u.statistic + 1 where u.uniqueCode=:param")
    void updateStatisticRequest(@Param("param") String uniqueCode);
}
