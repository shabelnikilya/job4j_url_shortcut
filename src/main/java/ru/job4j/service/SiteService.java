package ru.job4j.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.job4j.generated.RandomString;
import ru.job4j.model.Site;
import ru.job4j.model.SiteDTO;
import ru.job4j.model.Url;
import ru.job4j.repository.SiteRepository;

import java.util.Collections;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
public class SiteService implements Service, UserDetailsService {
    private final SiteRepository repository;
    private final RandomString random;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public SiteService(SiteRepository repository, RandomString random, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.random = random;
        this.encoder = encoder;
    }

    @Override
    public Site save(SiteDTO site) {
        String password = random.getRandom(10);
        Site saveSite = new Site();
        saveSite.setName(site.getName());
        saveSite.setLogin(random.getRandom(7));
        saveSite.setPassword(encoder.encode(password));
        repository.save(saveSite);
        saveSite.setPassword(password);
        return saveSite;
    }

    @Override
    public Site findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Site findByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }


    @Override
    public Site findByLogin(String login) {
        return repository.findByLogin(login).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        Site site = this.findByLogin(login);
        if (site == null) {
            log.error("This login not found: {}", login);
            throw new UsernameNotFoundException(login);
        }
        return new User(site.getLogin(), site.getPassword(), Collections.emptyList());
    }

    @Override
    public Integer getSiteStatistic(List<Url> urls) {
        return urls.stream()
                .map(Url::getStatistic)
                .reduce(0, Integer::sum);
    }
}
