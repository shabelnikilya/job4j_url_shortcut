package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Site;
import ru.job4j.model.SiteDTO;
import ru.job4j.model.Url;
import ru.job4j.service.Service;
import ru.job4j.service.SiteService;
import ru.job4j.service.UrlService;
import ru.job4j.service.UrlServiceInterface;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/")
public class SiteController {
    private final Service service;
    private final UrlServiceInterface urlService;

    @Autowired
    public SiteController(SiteService service, UrlService urlService) {
        this.service = service;
        this.urlService = urlService;
    }

    @PostMapping("/registration")
    public Map<String, String> registrationSite(@Valid @RequestBody SiteDTO site) {
        Site findSite = service.findByName(site.getName());
        boolean resultRegistration = findSite == null;
        Site result = resultRegistration ? service.save(site) : findSite;
        return Map.of(
                "registration", String.valueOf(resultRegistration),
                "login", result.getLogin(),
                "password", result.getPassword());
    }

    @GetMapping("/statistic")
    public List<Map<String, String>> getStatistic() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Site site = service.findByLogin(login);
        List<Url> urls = (List<Url>) urlService.findBySite(site);
        List<Map<String, String>> allStat = new ArrayList<>();
        allStat.add(
                Map.of("url", site.getName(), "total", String.valueOf(service.getSiteStatistic(urls)))
        );
        urls.forEach(
                u -> allStat.add(
                        Map.of("url", String.valueOf(u.getLink()), "total", String.valueOf(u.getStatistic()))
                )
        );
        return allStat;
    }
}
