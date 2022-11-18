package demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;
import java.util.regex.Pattern;

public class Checker {

    public static String chkIsValidInt(String input) {
        if (Objects.isNull(input)) {
            return input;
        }
        String regex = "\\d{1,10}";
        Pattern p = Pattern.compile(regex);
        return p.matcher(input).matches() ? input : "orz";
    }

    public static String chkSize(String input, Integer min, Integer max) {
        if (Objects.isNull(input)) {
            return input;
        }
        boolean a = Objects.isNull(min) ? true : min <= input.length();
        boolean b = Objects.isNull(max) ? true : input.length() <= max;
        return a && b ? input : "orz";
    }

    public static String chkYmd(String input) {
        if (Objects.isNull(input)) {
            return input;
        }
        try {
            LocalDate.parse(input, DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException dtpe) {
            return "orz";
        }
        return input;
    }

}
