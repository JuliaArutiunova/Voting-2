package by.it_academy.jd2.entity;

import java.time.LocalDateTime;

public class VoteEntity {
    private Long id;
    private String author;
    private LocalDateTime createAt;
    private Long artist;
    private Long[] genres;

    private VoteEntity(VoteEntity copy) {
        this.id = copy.id;
        this.author = copy.author;
        this.createAt = copy.createAt;
        this.artist = copy.artist;
        this.genres = copy.genres;
    }

    private VoteEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public Long getArtist() {
        return artist;
    }

    public Long[] getGenres() {
        return genres;
    }

    public static VoteEntity.Builder builder(){
        return new Builder();
    }


    public static class Builder{
        private VoteEntity instance = new VoteEntity();

        private Builder() {
        }

        public VoteEntity.Builder setID(Long id){
            this.instance.id = id;
            return this;
        }

        public VoteEntity.Builder setAuthor(String author){
            this.instance.author = author;
            return this;
        }

        public VoteEntity.Builder setCreateAt(LocalDateTime createAt){
            this.instance.createAt = createAt;
            return this;
        }

        public VoteEntity.Builder setArtist(Long artist){
            this.instance.artist = artist;
            return this;
        }

        public VoteEntity.Builder setGenres (Long[] genres){
            this.instance.genres = genres;
            return this;
        }

        public VoteEntity build(){
            return new VoteEntity(this.instance);
        }
    }
}
