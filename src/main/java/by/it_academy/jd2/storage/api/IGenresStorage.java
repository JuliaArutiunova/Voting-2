package by.it_academy.jd2.storage.api;



import java.util.ArrayList;
import java.util.Map;


public interface IGenresStorage {
    Long create(String genre);
    Long[] create(ArrayList<String> genresList);
    String get(Long id);
    Map<Long, String> get();


}
