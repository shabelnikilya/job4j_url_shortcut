package ru.job4j.generated;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomString implements Random {
    private final RandomStringGenerator generator;

    @Autowired
    public RandomString(RandomStringGenerator generator) {
        this.generator = generator;
    }

    @Override
    public String getRandom(int length) {
        return generator.generate(length);
    }
}
