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
        assertEquals(1, strings.size());
        assertEquals("tincas", strings.toString());
    }

    @Test
    public void twiceTest() {
        Strings strings = parser.parse("tincas trolha");
        assertEquals(2, strings.size());
        assertEquals("tincas,trolha", strings.toString());
    }

    @Test
    public void threeTimesTest() {
        Strings strings = parser.parse("tincas trolha dumbs");
        assertEquals(3, strings.size());
        assertEquals("tincas,trolha,dumbs", strings.toString());
    }

    @Test
    public void parenthesesTest() {
        Strings strings = recursiveParser.parse("(tincas)");
        assertEquals(1, strings.size());
        assertEquals("tincas", strings.toString());
    }

    @Test
    public void recursiveTest() {
        Strings strings = recursiveParser.parse("tincas (dunhas)");
        assertEquals(2, strings.size());
        assertEquals("tincas,dunhas", strings.toString());
    }

    @Test
    public void recursiveTestBad() {
        Strings strings = recursiveParser.parse("(tincas) (dunhas)");
        assertEquals(2, strings.size());
        assertEquals("tincas,dunhas", strings.toString());
    }

    @Test
    public void recursiveStartsParensTest() {
        Strings strings = recursiveParser.parse("(tincas (dunhas))");
        assertEquals(2, strings.size());
        assertEquals("tincas,dunhas", strings.toString());
    }

    @Test
    public void recursiveTwoLevelsTest() {
        Strings strings = recursiveParser.parse("(tincas (dunhas (js)))");
        assertEquals(3, strings.size());
        assertEquals("tincas,dunhas,js", strings.toString());
    }

    @Test
    public void recursiveManyLevelsTest() {
        Strings strings = recursiveParser.parse("(tincas (dunhas (socks (xpto cool))))");
        assertEquals(5, strings.size());
        assertEquals("tincas,dunhas,socks,xpto,cool", strings.toString());
    }
}