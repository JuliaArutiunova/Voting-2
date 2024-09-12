package by.it_academy.jd2.storage.db.factory;

import by.it_academy.jd2.storage.db.ArtistsStorageDB;

public class ArtistStorageFactory {
    private static final ArtistsStorageDB instance = new ArtistsStorageDB();

    private ArtistStorageFactory() {
    }

    public static ArtistsStorageDB getInstance() {
        return instance;
    }

}
