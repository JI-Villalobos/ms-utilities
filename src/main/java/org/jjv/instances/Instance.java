package org.jjv.instances;

import java.util.List;

public interface Instance <T>{
    void create(List<T> t);
    void create(T t);
    List<T> get();
    T getSingle();
}
