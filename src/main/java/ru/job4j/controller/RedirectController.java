package ru.job4j.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.model.Url;
import ru.job4j.service.UrlService;

import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/redirect")
public class RedirectController {
    private final UrlService service;

    @Autowired
    public RedirectController(UrlService service) {
        this.service = service;
    }

    @GetMapping("/{uniqueCode}")
    public ResponseEntity<String> redirect(@NotNull @PathVariable String uniqueCode) {
        Url findUrl = service.findByUniqueCode(uniqueCode);
        if (findUrl == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There is no url with this unique code"
            );
        }
        String originalUrl = findUrl.getLink();
        service.updateStatistic(uniqueCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", originalUrl);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
