package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.service.FormManagerService;
import by.it_academy.jd2.service.VotingService;
import by.it_academy.jd2.storage.factory.StorageFactory;

public class ServiceFactory {
    private static final FormManagerService formManagerService = new FormManagerService(StorageFactory.getArtistsStorage(),
            StorageFactory.getGenresStorage());
    private static final VotingService votingService = new VotingService(StorageFactory.getVotingStorage(),
            StorageFactory.getResultsStorage());

    private ServiceFactory() {
    }

    public static FormManagerService getFormManagerService() {
        return formManagerService;
    }

    public static VotingService getVotingService() {
        return votingService;
    }
}
