package by.it_academy.jd2.storage.db.factory;

import by.it_academy.jd2.storage.api.IConnectionManager;
import by.it_academy.jd2.storage.db.ConnectionManager;

public class ConnectionManagerFactory {
    private static final IConnectionManager instance = new ConnectionManager();

    private ConnectionManagerFactory() {
    }

    public static IConnectionManager getInstance(){
        return instance;
    }
}
