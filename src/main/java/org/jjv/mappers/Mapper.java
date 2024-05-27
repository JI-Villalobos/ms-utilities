package org.jjv.mappers;

import java.util.List;

public interface Mapper <T,V>{
    List<V> mapTo(List<T> list);
}
