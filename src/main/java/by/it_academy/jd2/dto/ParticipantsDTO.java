package by.it_academy.jd2.dto;

import java.util.Map;

public class ParticipantsDTO {
    private Map<Long, String> artists;
    private Map<Long, String> genres;

    private ParticipantsDTO(Map<Long, String> artists, Map<Long, String> genres) {
        this.artists = artists;
        this.genres = genres;
    }

    public Map<Long, String> getArtists() {
        return artists;
    }

    public Map<Long, String> getGenres() {
        return genres;
    }

    public static ParticipantsDTO.Builder builder() {
        return new Builder();
    }


    @Override
    public String toString() {
        return "ParticipantsDTO{" +
                "artists=" + artists +
                ", genres=" + genres +
                '}';
    }

    public static class Builder {
        private Map<Long, String> artists;
        private Map<Long, String> genres;

        public Builder() {
        }

        public Builder setArtists(Map<Long, String> artists) {
            this.artists = artists;
            return this;
        }

        public Builder setGenres(Map<Long, String> genres) {
            this.genres = genres;
            return this;
        }

        public ParticipantsDTO build() {
            return new ParticipantsDTO(this.artists, this.genres);
        }
    }
}
