package com.one2one.utils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class CollectionUtils<T> {

    public static boolean isEmpty(Collection<?> c) {
        return isNull(c) || c.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> c) {
        return !isEmpty(c);
    }

    public static <T> List<T> nullSafe(List<T> c) {
        return isNull(c) ? new ArrayList<>() : c;
    }

    public static <T> boolean containsAny(Collection<? extends T> list1, Collection<? super T> list2) {
        return list1.stream().anyMatch(list2::contains);
    }

    public static <T> List<T> distinct(List<T> l) {
        Set<T> set = new LinkedHashSet<>();
        set.addAll(l);
        l.clear();
        l.addAll(set);
        return l;
    }

    public static <T> List<T> clearList(List<T> l) {
        List<T> lists = l.stream()
                .collect(Collectors.toList());
        lists.clear();
        return lists;
    }
}
