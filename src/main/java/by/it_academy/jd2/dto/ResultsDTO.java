package by.it_academy.jd2.dto;

import by.it_academy.jd2.entity.Comment;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultsDTO {

    private List<Comment> comments;
    private Map<String, Long> artists;
    private Map<String, Long> genres;


    private ResultsDTO(Map<String, Long> artists, Map<String, Long> genres, List<Comment> comments) {
        this.artists = artists;
        this.genres = genres;
        this.comments = comments;
    }

    public List<Comment> getSortedComments() {
        comments.sort(Comparator.comparing(Comment::getDateTime).reversed());
        return comments;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public Map<String, Long> getArtists() {
        return artists;
    }

    public Map<String, Long> getGenres() {
        return genres;
    }

    public static ResultsDTO.Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private List<Comment> comments;
        private Map<String, Long> artists;
        private Map<String, Long> genres;

        private Builder() {
        }

        public Builder setComments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public Builder setArtists(Map<String, Long> artists) {
            this.artists = artists;
            return this;
        }

        public Builder setGenres(Map<String, Long> genres) {
            this.genres = genres;
            return this;
        }

        public ResultsDTO build(){
            return new ResultsDTO(this.artists,this.genres,this.comments);
        }
    }
}
