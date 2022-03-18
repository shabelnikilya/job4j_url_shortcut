package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.model.Site;
import ru.job4j.model.Url;
import ru.job4j.model.UrlDTO;
import ru.job4j.repository.SiteRepository;
import ru.job4j.repository.UrlRepository;
import ru.job4j.transformation.Transform;
import ru.job4j.transformation.TransformLink;

@Service
public class UrlService implements UrlServiceInterface {
    private final UrlRepository urlRepository;
    private final SiteRepository siteRepository;
    private final Transform transform;

    @Autowired
    public UrlService(UrlRepository urlRepository, TransformLink transform, SiteRepository siteRepository) {
        this.urlRepository = urlRepository;
        this.transform = transform;
        this.siteRepository = siteRepository;
    }

    @Override
    public Url save(UrlDTO url, String login) {
        Url saveUrl = new Url();
        saveUrl.setLink(url.getLink());
        saveUrl.setSite(siteRepository.findByLogin(login).orElse(null));
        saveUrl.setUniqueCode(transform.changeLink(url.getLink()));
        return urlRepository.save(saveUrl);
    }

    @Override
    public Url findById(int id) {
        return urlRepository.findById(id).orElse(null);
    }

    @Override
    public Url findByLink(String link) {
        return urlRepository.findByLink(link);
    }

    @Override
    public void deleteById(int id) {
        urlRepository.deleteById(id);
    }

    @Override
    public Url findByUniqueCode(String uniqueCode) {
        return urlRepository.findByUniqueCode(uniqueCode).orElse(null);
    }

    @Override
    public void updateStatistic(String uniqueCode) {
        urlRepository.updateStatisticRequest(uniqueCode);
    }

    @Override
    public Iterable<Url> findBySite(Site site) {
        return urlRepository.findBySite(site);
    }
}
