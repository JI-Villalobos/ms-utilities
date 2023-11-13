package org.jjv.persistence;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    void save(T t) throws SQLException;
    void saveAll(List<T> data) throws SQLException;
    List<T> findAll() throws SQLException;
    List<T> findAllById(Integer id) throws SQLException;
}
