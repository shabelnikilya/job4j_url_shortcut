package ru.job4j;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@SpringBootApplication
public class Job4jUrlShortcutApplication {

	@Bean
	public RandomStringGenerator stringGenerator() {
		return new RandomStringGenerator.Builder().withinRange(97, 122).build();
	}

	@Bean
	public StringBuilder transform() {
		return new StringBuilder();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringLiquibase liquibase(DataSource ds) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
		liquibase.setDataSource(ds);
		return liquibase;
	}

	public static void main(String[] args) {
		SpringApplication.run(Job4jUrlShortcutApplication.class, args);
	}
}
