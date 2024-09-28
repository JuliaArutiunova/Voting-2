package by.it_academy.jd2.storage.api;


import java.util.Map;


public interface IGenresStorage {
    Long create(String genre);

    String get(Long id);

    Map<Long, String> get();

    void delete(Long id);

    Long getByName(String name);
}