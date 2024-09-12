package by.it_academy.jd2.dto;

import java.util.ArrayList;


public class FormCreateDTO {

    private ArrayList<String> newArtists;
    private ArrayList<String> newGenres;

    private FormCreateDTO(ArrayList<String> newArtists, ArrayList<String> newGenres) {
        this.newArtists = newArtists;
        this.newGenres = newGenres;
    }

    public ArrayList<String> getNewArtists() {
        return newArtists;
    }

    public ArrayList<String> getNewGenres() {
        return newGenres;
    }

    public static FormCreateDTO.Builder builder() {
        return new Builder();
    }


    @Override
    public String toString() {
        return "FormCreateDTO{" +
                "newArtists=" + newArtists +
                ", newGenres=" + newGenres +
                '}';
    }

    public static class Builder {
        private ArrayList<String> newArtists;
        private ArrayList<String> newGenres;

        private Builder() {
        }

        public FormCreateDTO.Builder setNewArtists(ArrayList<String> newArtists) {
            this.newArtists = newArtists;
            return this;
        }

        public FormCreateDTO.Builder setNewGenres(ArrayList<String> newGenres) {
            this.newGenres = newGenres;
            return this;
        }


        public FormCreateDTO build() {
            return new FormCreateDTO(this.newArtists, this.newGenres);
        }
    }
}
