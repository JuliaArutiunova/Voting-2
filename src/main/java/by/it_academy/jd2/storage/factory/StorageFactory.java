package by.it_academy.jd2.storage.factory;

import by.it_academy.jd2.storage.api.IArtistsStorage;
import by.it_academy.jd2.storage.api.IGenresStorage;
import by.it_academy.jd2.storage.api.IResultsStorage;
import by.it_academy.jd2.storage.api.IVotingStorage;
import by.it_academy.jd2.storage.db.factory.ArtistStorageFactory;
import by.it_academy.jd2.storage.db.factory.GenresStorageFactory;
import by.it_academy.jd2.storage.db.factory.ResultsStorageFactory;
import by.it_academy.jd2.storage.db.factory.VotingStorageFactory;

public class StorageFactory {
    private static IArtistsStorage artistsStorage = ArtistStorageFactory.getInstance();
    private static IGenresStorage genresStorage = GenresStorageFactory.getInstance();
    private static IVotingStorage votingStorage = VotingStorageFactory.getInstance();

    private static IResultsStorage resultsStorage = ResultsStorageFactory.getInstance();

    private StorageFactory() {
    }

    public static IArtistsStorage getArtistsStorage() {
        return artistsStorage;
    }

    public static IGenresStorage getGenresStorage() {
        return genresStorage;
    }

    public static IVotingStorage getVotingStorage() {
        return votingStorage;
    }

    public static IResultsStorage getResultsStorage(){
        return resultsStorage;
    }
}
