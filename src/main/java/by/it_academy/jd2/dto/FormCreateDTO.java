package by.it_academy.jd2.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class FormCreateDTO {

    private String votingName;
    private ArrayList<String> newArtists;
    private ArrayList<String> newGenres;
    private LocalDateTime start;

    private FormCreateDTO( String votingName, ArrayList<String> newArtists,
                           ArrayList<String> newGenres, LocalDateTime start) {
        this.votingName = votingName;
        this.newArtists = newArtists;
        this.newGenres = newGenres;
        this.start = start;
    }

    public ArrayList<String> getNewArtists() {
        return newArtists;
    }

    public ArrayList<String> getNewGenres() {
        return newGenres;
    }

    public String getVotingName() {
        return votingName;
    }

    public static FormCreateDTO.Builder builder() {
        return new Builder();
    }


    @Override
    public String toString() {
        return "FormCreateDTO{" +
                "votingName='" + votingName + '\'' +
                ", newArtists=" + newArtists +
                ", newGenres=" + newGenres +
                ", start=" + start +
                '}';
    }

    public static class Builder {
        private String votingName;
        private ArrayList<String> newArtists;
        private ArrayList<String> newGenres;
        private LocalDateTime start;

        private Builder() {
        }

        public FormCreateDTO.Builder setVotingName(String votingName){
            this.votingName = votingName;
            return this;
        }

        public FormCreateDTO.Builder setNewArtists(ArrayList<String> newArtists) {
            this.newArtists = newArtists;
            return this;
        }

        public FormCreateDTO.Builder setNewGenres(ArrayList<String> newGenres) {
            this.newGenres = newGenres;
            return this;
        }

        public FormCreateDTO.Builder setStart(LocalDateTime start){
            this.start = start;
            return this;
        }


        public FormCreateDTO build() {
            return new FormCreateDTO(
                    this.votingName,
                    this.newArtists,
                    this.newGenres,
                    this.start);
        }
    }
}
