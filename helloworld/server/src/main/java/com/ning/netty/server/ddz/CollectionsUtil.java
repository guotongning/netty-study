package com.ning.netty.server.ddz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionsUtil {
    public static <T> List<List<T>> splitListBySize(List<T> list, int limit) {
        int stepCount = (list.size() + limit - 1) / limit;
        return Stream
                .iterate(0, n -> n + 1)
                .limit(stepCount).parallel()
                .map(a -> list.stream().skip(a * limit).limit(limit).parallel().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
