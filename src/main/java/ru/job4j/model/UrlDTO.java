package ru.job4j.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UrlDTO {
    @NotBlank(message = "The UrlDTO link cannot be empty")
    private String link;
}
