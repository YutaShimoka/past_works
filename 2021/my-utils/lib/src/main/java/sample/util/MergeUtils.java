package sample.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * マージに関するユーティリティ。
 */
public abstract class MergeUtils {

    @SuppressWarnings("unchecked")
    public static List<Object> mergeList(Object... objects) {
        return (List<Object>) mergeList(ListType.OBJECT.getClazz(), objects);
    }

    public static List<?> mergeList(Class<?> clazz, Object... objects) {

        List<List<?>> listList = new ArrayList<>();

        for (Object obj : objects) {
            if (List.class.isInstance(obj)) {
                listList.add((List<?>) obj);
            }
        }

        List<?> mergeList = Collections.emptyList();

        for (List<?> list : listList) {
            mergeList = Stream.concat(mergeList.stream(), list.stream()).collect(Collectors.toList());
        }
        return mergeList.stream().map(clazz::cast).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public static List<String> mergeStrList(Object... objects) {
        return (List<String>) mergeList(ListType.STRING.getClazz(), objects);
    }

    @SuppressWarnings("unchecked")
    public static List<Long> mergeLongList(Object... objects) {
        return (List<Long>) mergeList(ListType.LONG.getClazz(), objects);
    }

    @SuppressWarnings("unchecked")
    public static List<Integer> mergeIntList(Object... objects) {
        return (List<Integer>) mergeList(ListType.INTEGER.getClazz(), objects);
    }

    @SuppressWarnings("unchecked")
    public static List<Character> mergeCharList(Object... objects) {
        return (List<Character>) mergeList(ListType.CHARACTER.getClazz(), objects);
    }

    private enum ListType {

        OBJECT(Object.class),

        STRING(String.class),

        LONG(Long.class),

        INTEGER(Integer.class),

        CHARACTER(Character.class);

        private final Class<?> clazz;

        ListType(Class<?> clazz) {
            this.clazz = clazz;
        }

        public Class<?> getClazz() {
            return clazz;
        }
    }
}
