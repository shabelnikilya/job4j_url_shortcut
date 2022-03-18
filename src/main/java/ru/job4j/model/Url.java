package ru.job4j.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "The link cannot be empty")
    private String link;
    @Column(name = "unique_code")
    private String uniqueCode;
    private int statistic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;
}
