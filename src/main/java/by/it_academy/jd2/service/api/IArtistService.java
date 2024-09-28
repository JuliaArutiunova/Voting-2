package by.it_academy.jd2.service.api;

import java.util.Map;

public interface IArtistService {
    void create(String name);

    String get(Long id);

    Map<Long, String> get();

    void delete(Long id);

}
