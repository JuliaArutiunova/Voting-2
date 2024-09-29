package by.it_academy.jd2.validation.factory;


import by.it_academy.jd2.service.factory.ArtistServiceFactory;
import by.it_academy.jd2.service.factory.GenreServiceFactory;
import by.it_academy.jd2.validation.VotingFormValidator;
import by.it_academy.jd2.validation.api.IVotingFormValidator;

public class ValidatorFactory {
    private static final IVotingFormValidator instance = new VotingFormValidator(ArtistServiceFactory.getInstance(),
           GenreServiceFactory.getInstance());

    private ValidatorFactory() {
    }

    public static IVotingFormValidator getInstance(){
        return instance;
    }


}
