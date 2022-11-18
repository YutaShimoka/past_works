package sample.util;

import static org.junit.Assert.*;
import static sample.util.ConvertUtilsTest.Const.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import sample.util.ConvertUtils.ConvertException;

public class ConvertUtilsTest {

    /** Longへ変換をテストします。 */
    @Test
    public void testGetValueAsLong() {
        var result = ConvertUtils.getValueAsLong("1");
        boolean b = result instanceof Long;
        assertTrue(b);
        assertNull(ConvertUtils.getValueAsLong(null));
    }

    @Test(expected = ConvertException.class)
    public void testGetValueAsLongError() {
        ConvertUtils.getValueAsLong("asdf");
    }

    /** Integerへ変換をテストします。 */
    @Test
    public void testGetValueAsInteger() {
        var result = ConvertUtils.getValueAsInteger("1");
        boolean b = result instanceof Integer;
        assertTrue(b);
        assertNull(ConvertUtils.getValueAsInteger(null));
    }

    @Test(expected = ConvertException.class)
    public void testGetValueAsIntegerError() {
        ConvertUtils.getValueAsInteger("asdf");
    }

    /** Characterへ変換をテストします。 */
    @Test
    public void testGetValueAsCharacter() {
        var result = ConvertUtils.getValueAsCharacter("あ");
        boolean b = result instanceof Character;
        assertTrue(b);
        assertNull(ConvertUtils.getValueAsCharacter(null));
    }

    @Test(expected = ConvertException.class)
    public void testGetValueAsCharacterError() {
        ConvertUtils.getValueAsCharacter("あｓ");
    }

    /** 例外無しに{@literal List<Long>}へ変換をテストします。 */
    @Test
    public void testConvertAllToLong() {

        assertEquals(LONG_LIST, ConvertUtils.convertAllToLong(LIST));

        assertEquals(LONG_LIST, ConvertUtils.convertAllToLong(STRING_LIST));

        assertEquals(LONG_LIST, ConvertUtils.convertAllToLong(LONG_LIST));

        assertEquals(LONG_LIST, ConvertUtils.convertAllToLong(INTEGER_LIST));

        assertEquals(LONG_LIST, ConvertUtils.convertAllToLong(CHARACTER_LIST));

        assertEquals(Collections.emptyList(), ConvertUtils.convertAllToLong(ABNORMAL_LIST));

        assertEquals(Collections.emptyList(), ConvertUtils.convertAllToLong(null));
    }

    /** 例外無しに{@literal List<Integer>}へ変換をテストします。 */
    @Test
    public void testConvertAllToInteger() {

        assertEquals(INTEGER_LIST, ConvertUtils.convertAllToInteger(LIST));

        assertEquals(INTEGER_LIST, ConvertUtils.convertAllToInteger(STRING_LIST));

        assertEquals(INTEGER_LIST, ConvertUtils.convertAllToInteger(LONG_LIST));

        assertEquals(INTEGER_LIST, ConvertUtils.convertAllToInteger(INTEGER_LIST));

        assertEquals(INTEGER_LIST, ConvertUtils.convertAllToInteger(CHARACTER_LIST));

        assertEquals(Collections.emptyList(), ConvertUtils.convertAllToInteger(ABNORMAL_LIST));

        assertEquals(Collections.emptyList(), ConvertUtils.convertAllToInteger(null));
    }

    /** 例外無しに{@literal List<Character>}へ変換をテストします。 */
    @Test
    public void testConvertAllToCharacter() {

        assertEquals(CHARACTER_LIST, ConvertUtils.convertAllToCharacter(LIST));

        assertEquals(CHARACTER_LIST, ConvertUtils.convertAllToCharacter(STRING_LIST));

        assertEquals(CHARACTER_LIST, ConvertUtils.convertAllToCharacter(LONG_LIST));

        assertEquals(CHARACTER_LIST, ConvertUtils.convertAllToCharacter(INTEGER_LIST));

        assertEquals(CHARACTER_LIST, ConvertUtils.convertAllToCharacter(CHARACTER_LIST));

        assertEquals(Collections.emptyList(), ConvertUtils.convertAllToCharacter(ABNORMAL_LIST_2));

        assertEquals(Collections.emptyList(), ConvertUtils.convertAllToCharacter(null));
    }

    public static class Const {

        private static final List<Object> LIST = Collections.unmodifiableList(Arrays.asList("0", 1L, 2, '3'));

        private static final List<Object> STRING_LIST = Collections.unmodifiableList(Arrays.asList("0", "1", "2", "3"));

        private static final List<Object> LONG_LIST = Collections.unmodifiableList(Arrays.asList(0L, 1L, 2L, 3L));

        private static final List<Object> INTEGER_LIST = Collections.unmodifiableList(Arrays.asList(0, 1, 2, 3));

        private static final List<Object> CHARACTER_LIST = Collections.unmodifiableList(Arrays.asList('0', '1', '2', '3'));

        private static final List<Object> ABNORMAL_LIST = Collections.unmodifiableList(Arrays.asList("asdf"));

        private static final List<Object> ABNORMAL_LIST_2 = Collections.unmodifiableList(Arrays.asList("あｓ"));
    }

}
