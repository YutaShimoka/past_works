package sample.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** 各種型変換をサポートします。 */
public abstract class ConvertUtils {

    /** Longへ変換します。 */
    public static Long getValueAsLong(String value) {
        try {
            return Optional.ofNullable(value).map(Long::parseLong).orElse(null);
        } catch (NumberFormatException nfe) {
            throw new ConvertException("Longへの変換に失敗しました", value, nfe);
        }
    }

    /** 例外無しに{@literal List<Long>}へ変換します。(変換できない時は空のコレクション) */
    public static List<Long> convertAllToLong(List<Object> list) {
        try {
            return list.stream().map(Object::toString)
                    .map(ConvertUtils::getValueAsLong).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /** Integerへ変換します。 */
    public static Integer getValueAsInteger(String value) {
        try {
            return Optional.ofNullable(value).map(Integer::parseInt).orElse(null);
        } catch (NumberFormatException nfe) {
            throw new ConvertException("Integerへの変換に失敗しました", value, nfe);
        }
    }

    /** 例外無しに{@literal List<Integer>}へ変換します。(変換できない時は空のコレクション) */
    public static List<Integer> convertAllToInteger(List<Object> list) {
        try {
            return list.stream().map(Object::toString)
                    .map(ConvertUtils::getValueAsInteger).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /** Characterへ変換します。 */
    public static Character getValueAsCharacter(String value) {
        if (value != null && value.length() != 1) {
            throw new ConvertException("Characterへの変換に失敗しました", value, null);
        }
        return Optional.ofNullable(value).map(v -> v.charAt(0)).orElse(null);
    }

    /** 例外無しに{@literal List<Character>}へ変換します。(変換できない時は空のコレクション) */
    public static List<Character> convertAllToCharacter(List<Object> list) {
        try {
            return list.stream().map(Object::toString)
                    .map(ConvertUtils::getValueAsCharacter).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public static class ConvertException extends IllegalArgumentException {

        public ConvertException(String message, String value, Throwable cause) {
            super(String.format("%s (input string: \"%s\")", message, value), cause);
        }
    }

}
