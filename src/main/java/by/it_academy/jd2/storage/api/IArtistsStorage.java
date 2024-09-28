package by.it_academy.jd2.storage.api;


import java.util.Map;


public interface IArtistsStorage {

    Long create(String artist);

    Map<Long, String> get();

    String getById(Long id);

    Long getByName(String name);

    void delete(Long id);

}
