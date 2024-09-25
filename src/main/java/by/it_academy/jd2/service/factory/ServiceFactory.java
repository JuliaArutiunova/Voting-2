package by.it_academy.jd2.service.factory;



import by.it_academy.jd2.service.VoteService;
import by.it_academy.jd2.service.api.IArtistService;
import by.it_academy.jd2.service.api.IGenreService;
import by.it_academy.jd2.storage.factory.StorageFactory;
import by.it_academy.jd2.validation.VotingFormValidator;

public class ServiceFactory {

    private static final IArtistService artistService = ArtistServiceFactory.getInstance();
    private static final IGenreService genreService = GenreServiceFactory.getInstance();
    private static final VoteService votingService = new VoteService(StorageFactory.getVotingStorage(),
            new VotingFormValidator(artistService,genreService));


    private ServiceFactory() {
    }


    public static VoteService getVotingService() {
        return votingService;
    }

    public static IArtistService getArtistService() {
        return artistService;
    }

    public static IGenreService getGenreService(){
        return genreService;
    }


}
