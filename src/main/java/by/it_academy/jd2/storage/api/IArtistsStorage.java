package by.it_academy.jd2.storage.api;


import java.util.ArrayList;
import java.util.Map;


public interface IArtistsStorage {

    Long create(String artist);

    Long[] create(ArrayList<String> artistsList);

    Map<Long, String> get();

    String get(Long id);

    String delete(Long id);
}
