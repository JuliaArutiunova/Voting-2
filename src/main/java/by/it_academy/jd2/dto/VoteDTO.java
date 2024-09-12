package by.it_academy.jd2.dto;


import java.time.LocalDateTime;
import java.util.Arrays;

public class VoteDTO {

    private InfoFromClientDTO info;
    private LocalDateTime createAt;


    public VoteDTO(InfoFromClientDTO info, LocalDateTime createAt) {
        this.info = info;
        this.createAt = createAt;
    }


    public long[] getGenres() {
        return Arrays.stream(info.getGenres()).mapToLong(Long::parseLong).toArray();
    }

    public long getArtist() {
        return Long.parseLong(info.getArtist());
    }

    public InfoFromClientDTO getInfo() {
        return info;
    }

    public String getAuthor() {
        return info.getName();
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public String getText() {
        return info.getComment();
    }

    public void setInfo(InfoFromClientDTO info) {
        this.info = info;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "VoteDTO{" +
                "info=" + info +
                ", createAt=" + createAt +
                '}';
    }
}
