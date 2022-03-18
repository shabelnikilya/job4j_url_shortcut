package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Url;
import ru.job4j.model.UrlDTO;
import ru.job4j.service.UrlService;
import ru.job4j.service.UrlServiceInterface;

import javax.validation.Valid;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/url")
public class UrlController {
    private final UrlServiceInterface service;

    @Autowired
    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/reg")
    public Map<String, String> regUrl(@Valid @RequestBody UrlDTO urlDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Url url = service.save(urlDTO, login);
        return Map.of("code", url.getUniqueCode());
    }
}
