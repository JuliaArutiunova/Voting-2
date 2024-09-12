package by.it_academy.jd2.entity;

import java.time.LocalDateTime;

public class Comment {
    private String author;
    private String text;
    private LocalDateTime dateTime;

    private Comment(Comment copy) {
        this.author = copy.author;
        this.text = copy.text;
        this.dateTime = copy.dateTime;
    }

    private Comment() {
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public static Comment.Builder builder(){
        return new Builder();
    }


    @Override
    public String toString() {
        return "Comment{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    public static class Builder {
        private Comment instance = new Comment();

        private Builder() {
        }

        public Comment.Builder setAuthor(String author) {
            this.instance.author = author;
            return this;
        }

        public Comment.Builder setText(String text) {
            this.instance.text = text;
            return this;
        }

        public Comment.Builder setDateTime(LocalDateTime dateTime) {
            this.instance.dateTime = dateTime;
            return this;
        }

        public Comment build() {
            return new Comment(this.instance);
        }
    }


}
