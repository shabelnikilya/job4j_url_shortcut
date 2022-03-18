package ru.job4j.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "The site name cannot be empty")
    private String name;
    @NotBlank(message = "The site login cannot be empty")
    private String login;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date(System.currentTimeMillis());
    @OneToMany(mappedBy = "site")
    private Set<Url> url = new HashSet<>();

    public Site(String name) {
        this.name = name;
    }
}
