package org.lesson10.imdb;

public class MovieInfo {
    private final String title;
    private final String link;
    private final String year;
    private final String rating;

    public MovieInfo(String title, String link, String year, String rating) {
        this.title = title;
        this.link = link;
        this.year = year;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }
}
