package io.zgc.design.patterns;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JavaMemoryPageUtil {
    public static <T> List<T> getPageLimit(Collection<T> data, long curPage, long pageSize) {
        return data.stream().skip((curPage-1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }
}