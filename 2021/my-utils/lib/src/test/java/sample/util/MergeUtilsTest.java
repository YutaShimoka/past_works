package sample.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MergeUtilsTest {

    @Test
    public void testMergeList() {

        List<Object> list = Arrays.asList("1", 2L, 3, '4');
        List<Object> list1 = list;
        List<Object> list2 = list1;

        List<Object> mergeList = MergeUtils.mergeList(list, list1, list2);
        assertEquals(12, mergeList.size());
    }

    public static class A {

        private static List<A> getListForTest() {
            return Arrays.asList(new A());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<A> mergeTestList(Object... objects) {
        return (List<A>) MergeUtils.mergeList(A.class, objects);
    }

    @Test
    public void testMergeList1() {

        List<A> list = A.getListForTest();
        List<A> list1 = list;

        List<A> mergeList = mergeTestList(list, list1);
        assertEquals(2, mergeList.size());

        list = null;

        List<A> mergeList1 = mergeTestList(list);
        assertEquals(0, mergeList1.size());

        List<A> mergeList2 = mergeTestList(list, list1);
        assertEquals(1, mergeList2.size());

        list1 = null;

        List<A> mergeList3 = mergeTestList(list, list1);
        assertEquals(0, mergeList3.size());

        List<A> mergeList4 = mergeTestList(list, list1, new ArrayList<A>());
        assertEquals(0, mergeList4.size());
    }

    @Test(expected = ClassCastException.class)
    public void testMergeListError() {
        MergeUtils.mergeList(A.class, Arrays.asList("asdf"));
    }

    @Test(expected = ClassCastException.class)
    public void testMergeListError1() {
        MergeUtils.mergeList(String.class, A.getListForTest());
    }

    @Test
    public void testMergeStrList() {

        List<String> strList = Arrays.asList("1");

        List<String> mergeList = MergeUtils.mergeStrList(strList);
        assertEquals(1, mergeList.size());
    }

    @Test
    public void testMergeLongList() {

        List<Long> longList = Arrays.asList(1L, 2L);
        List<Long> longList1 = longList;

        List<Long> mergeList = MergeUtils.mergeLongList(longList, longList1);
        assertEquals(4, mergeList.size());
    }

    @Test
    public void testMergeIntList() {

        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Integer> intList1 = intList;
        List<Integer> intList2 = intList1;

        List<Integer> mergeList = MergeUtils.mergeIntList(intList, intList1, intList2);
        assertEquals(9, mergeList.size());
    }

    @Test
    public void testMergeCharList() {

        List<Character> charList = Arrays.asList('1', '2', '3', '4');
        List<Character> charList1 = charList;
        List<Character> charList2 = charList1;
        List<Character> charList3 = charList2;

        List<Character> mergeList = MergeUtils.mergeCharList(charList, charList1, charList2, charList3);
        assertEquals(16, mergeList.size());
    }

}
