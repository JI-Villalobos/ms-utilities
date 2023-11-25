package org.jjv.readers;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public interface Reader <T>{
    List<T> collectData(Sheet sheet);
}
