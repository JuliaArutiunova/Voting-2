package by.it_academy.jd2.dto;


public class InfoFromClientDTO {
    private String name;
    private String artist;
    private String[] genres;
    private String comment;

    private InfoFromClientDTO(InfoFromClientDTO copy) {
        this.name = copy.name;
        this.artist = copy.artist;
        this.genres = copy.genres;
        this.comment = copy.comment;
    }

    private InfoFromClientDTO() {
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getComment() {
        return comment;
    }

    public static InfoFromClientDTO.Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private InfoFromClientDTO instance = new InfoFromClientDTO();

        private Builder() {
        }

        public InfoFromClientDTO.Builder setName(String name) {
            this.instance.name = name;
            return this;
        }

        public InfoFromClientDTO.Builder setArtist(String artist) {
            this.instance.artist = artist;
            return this;
        }

        public InfoFromClientDTO.Builder setGenres(String[] genres) {
            this.instance.genres = genres;
            return this;
        }

        public InfoFromClientDTO.Builder setComment(String comment) {
            this.instance.comment = comment;
            return this;
        }

        public InfoFromClientDTO build() {
            return new InfoFromClientDTO(this.instance);
        }
    }


}
