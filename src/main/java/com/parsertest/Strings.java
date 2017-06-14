package com.parsertest;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Strings {
    private List<String> strings;

    Strings(List<Strings> strings) {
        this.strings = strings.stream().map(Strings::getStrings).collect(toList());
    }

    Strings(Collection<String> strings) {
        this.strings = (List<String>) strings;
    }

    public String getStrings() {
        return strings.stream().collect(joining(","));
    }
}