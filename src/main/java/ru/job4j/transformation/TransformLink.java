package ru.job4j.transformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TransformLink implements Transform {
    private final StringBuilder str;

    @Autowired
    public TransformLink(StringBuilder str) {
        this.str = str;
    }

    @Override
    public String changeLink(String link) {
        List<Character> rsl = fromStringToList(link);
        Collections.shuffle(rsl);
        rsl.stream()
                .filter(ch -> ch != '/' && ch != '.' && ch != '-')
                .forEach(str::append);
        return str.toString();
    }

    public List<Character> fromStringToList(String link) {
        List<Character> rsl = new ArrayList<>();
        for (int i = 0; i < link.length(); i += 2) {
            rsl.add(link.charAt(i));
        }
        return rsl;
    }
}
