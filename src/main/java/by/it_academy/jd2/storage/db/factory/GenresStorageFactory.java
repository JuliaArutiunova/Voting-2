package by.it_academy.jd2.storage.db.factory;

import by.it_academy.jd2.storage.db.GenresStorageDB;

public class GenresStorageFactory {
    private static final GenresStorageDB instance = new GenresStorageDB();

    private GenresStorageFactory() {
    }

    public static GenresStorageDB getInstance() {
        return instance;
    }
}
