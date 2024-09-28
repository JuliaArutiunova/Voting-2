package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.service.VoteService;
import by.it_academy.jd2.storage.factory.StorageFactory;
import by.it_academy.jd2.validation.VotingFormValidator;

public class VoteServiceFactory {

    private static final VoteService instance= new VoteService(StorageFactory.getVotingStorage(),
            new VotingFormValidator(ArtistServiceFactory.getInstance(),GenreServiceFactory.getInstance()));

    private VoteServiceFactory() {
    }

    public static VoteService getInstance(){
        return instance;
    }
}
