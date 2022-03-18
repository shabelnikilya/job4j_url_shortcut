package ru.job4j.service;

import ru.job4j.model.Site;
import ru.job4j.model.Url;
import ru.job4j.model.UrlDTO;

public interface UrlServiceInterface {

    Url save(UrlDTO url, String login);

    Url findById(int id);

    Url findByLink(String link);

    void deleteById(int id);

    Url findByUniqueCode(String uniqueCode);

    void updateStatistic(String uniqueCode);

    Iterable<Url> findBySite(Site site);
}
