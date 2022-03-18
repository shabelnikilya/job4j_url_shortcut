package ru.job4j.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SiteDTO {
    @NotBlank(message = "The siteDTO name cannot be empty")
    private String name;
}
