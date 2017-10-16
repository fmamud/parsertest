package com.parsertest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Strings {
    private List<String> strings;

    Strings(Collection<String> strings) {
        this.strings = new ArrayList<>(strings);
    }

    Strings(List<List<Strings>> strings) {
        this.strings = strings.stream()
                .flatMap(List::stream)
                .flatMap(s -> s.strings.stream())
                .collect(toList());
    }

    public int size() {
        return strings.size();
    }

    @Override
    public String toString() {
        return strings.stream().collect(joining(","));
    }
}