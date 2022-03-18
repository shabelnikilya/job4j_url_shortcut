package ru.job4j.service;

import ru.job4j.model.Site;
import ru.job4j.model.SiteDTO;
import ru.job4j.model.Url;

import java.util.List;

public interface Service {

    Site save(SiteDTO site);

    Site findById(int id);

    Site findByLogin(String login);

    Site findByName(String name);

    void deleteById(int id);

    Integer getSiteStatistic(List<Url> urls);
}
