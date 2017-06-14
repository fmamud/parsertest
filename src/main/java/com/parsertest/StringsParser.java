package com.parsertest;

import org.jparsec.Parser;
import org.jparsec.Scanners;

import static org.jparsec.Scanners.isChar;

public class StringsParser {
    private static Parser<Strings> STRING =
            Scanners.IDENTIFIER.sepBy(Scanners.WHITESPACES)
                    .map(Strings::new);

    public static Parser<Strings> get() {
        return STRING;
    }

    public static Parser<Strings> getRecursive() {
        Parser.Reference<Strings> ref = Parser.newReference();

        Parser<Strings> parser = ref.lazy().between(isChar('('), isChar(')')).or(STRING)
                .sepBy(Scanners.WHITESPACES)
                .map(Strings::new);
        ref.set(parser);
        return parser;
    }
}
