package by.it_academy.jd2.storage.db.factory;

import by.it_academy.jd2.storage.db.VotingStorageDB;

public class VotingStorageFactory {
    private static final VotingStorageDB instance = new VotingStorageDB();

    private VotingStorageFactory() {
    }
    public static VotingStorageDB getInstance() {
        return instance;
    }
}
