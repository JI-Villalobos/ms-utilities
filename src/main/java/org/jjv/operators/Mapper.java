package org.jjv.operators;

import java.util.List;

public interface Mapper <T,V>{
    List<V> mapTo(List<T> list);
}
