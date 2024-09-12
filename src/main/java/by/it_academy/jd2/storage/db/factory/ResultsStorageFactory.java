package by.it_academy.jd2.storage.db.factory;

import by.it_academy.jd2.storage.db.ResultStorageDB;

public class ResultsStorageFactory {
    private static final ResultStorageDB instance = new ResultStorageDB();

    private ResultsStorageFactory() {
    }

    public static ResultStorageDB getInstance(){
        return instance;
    }
}
