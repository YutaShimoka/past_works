package sample.util;

import static org.junit.Assert.*;
import static sample.util.Checker.*;

import org.junit.Test;

public class CheckerTest {

    @Test
    public void testIsCsv() {
        assertTrue(isCsv("asdf.csv"));
        assertTrue(isCsv(".csv"));
        assertFalse(isCsv("asdf.f!csv"));
        assertFalse(isCsv(null));
        assertFalse(isCsv(""));
        assertFalse(isCsv(" "));
        assertFalse(isCsv("　"));
        assertFalse(isCsv("asdf.csv.gz"));
    }

    @Test
    public void testIsCsvGz() {
        assertTrue(isCsvGz("asdf.csv.gz"));
        assertTrue(isCsvGz(".csv.gz"));
        assertFalse(isCsvGz("asdf.f!csv.gz"));
        assertFalse(isCsvGz("asdf.csv.f!gz"));
        assertFalse(isCsvGz("asdf.f!csv.f!gz"));
        assertFalse(isCsvGz(null));
        assertFalse(isCsvGz(""));
        assertFalse(isCsvGz(" "));
        assertFalse(isCsvGz("　"));
        assertFalse(isCsvGz("asdf.csv"));
    }

}
