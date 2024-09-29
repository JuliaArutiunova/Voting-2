package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.service.VoteService;
import by.it_academy.jd2.service.api.IVoteService;
import by.it_academy.jd2.storage.db.factory.VotingStorageFactory;
import by.it_academy.jd2.validation.factory.ValidatorFactory;

public class VoteServiceFactory {


    private static final IVoteService instance = new VoteService(VotingStorageFactory.getInstance()
    ,ValidatorFactory.getInstance());

    private VoteServiceFactory() {
    }

    public static IVoteService getInstance() {
        return instance;
    }


}


