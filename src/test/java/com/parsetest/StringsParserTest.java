package com.parsetest;

import com.parsertest.Strings;
import com.parsertest.StringsParser;
import org.jparsec.Parser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringsParserTest {

    private Parser<Strings> parser = StringsParser.get();

    private Parser<Strings> recursiveParser = StringsParser.getRecursive();

    @Test
    public void simpleTest() {
        Strings strings = parser.parse("tincas");
        assertEquals("tincas", strings.getStrings());
    }

    @Test
    public void twiceTest() {
        Strings strings = parser.parse("tincas trolha");
        assertEquals("tincas,trolha", strings.getStrings());
    }

    @Test
    public void threeTimesTest() {
        Strings strings = parser.parse("tincas trolha dumbs");
        assertEquals("tincas,trolha,dumbs", strings.getStrings());
    }

    @Test
    public void parenthesesTest() {
        Strings strings = recursiveParser.parse("(tincas)");
        assertEquals("tincas", strings.getStrings());
    }

    @Test
    public void recursiveTest() {
        Strings strings = recursiveParser.parse("tincas (dunhas)");
        assertEquals("tincas,dunhas", strings.getStrings());
    }

    @Test
    public void recursiveStartsParensTest() {
        Strings strings = recursiveParser.parse("(tincas (dunhas))");
        assertEquals("tincas,dunhas", strings.getStrings());
    }

    @Test
    public void recursiveTwoLevelsTest() {
        Strings strings = recursiveParser.parse("(tincas (dunhas (js)))");
        assertEquals("tincas,dunhas,js", strings.getStrings());
    }

    @Test
    public void recursiveManyLevelsTest() {
        Strings strings = recursiveParser.parse("(tincas (dunhas (socks (xpto cool))))");
        assertEquals("tincas,dunhas,socks,xpto,cool", strings.getStrings());
    }
}