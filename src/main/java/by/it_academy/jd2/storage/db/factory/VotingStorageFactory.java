package by.it_academy.jd2.storage.db.factory;

import by.it_academy.jd2.storage.db.ConnectionManager;
import by.it_academy.jd2.storage.db.VoteStorageDB;

public class VotingStorageFactory {

    private static final VoteStorageDB instance = new VoteStorageDB(ConnectionManagerFactory.getInstance());

    private VotingStorageFactory() {
    }
    public static VoteStorageDB getInstance() {
        return instance;
    }
}
