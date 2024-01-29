package org.lesson10.imdb;

import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImdbApi {

    public final ImdbRestClient client = new ImdbRestClient();

    public ResponseBody getTopChartResponse() throws IOException {

        return client.imdbServiceInterface.getTopChart().execute().body();
    }

    public ResponseBody getMoviePageResponse(String route) throws IOException {
        System.out.println(client.imdbServiceInterface.getMoviePage(route).execute());
        return client.imdbServiceInterface.getMoviePage(route).execute().body();
    }


    public List<MovieInfo> getTop100Films() throws IOException {
        return getTopChart().stream().limit(100).toList();
    }

    public List<MovieInfo> getTopChart() throws IOException {
        var htmlString = getTopChartResponse().string();
        var document = Jsoup.parse(htmlString);


        Elements listItems = document.select("ul.ipc-metadata-list > li");

        // Create a list to store movie information
        List<MovieInfo> movies = new ArrayList<>();

        // Iterate through each list item and extract information
        for (Element listItem : listItems) {
            MovieInfo movieInfo = extractMovieInfoFromListPage(listItem);
            movies.add(movieInfo);
        }

        return movies;
    }

    private static MovieInfo extractMovieInfoFromListPage(Element listItem) {
        // Extract movie information from a single list item
        String title = listItem.select("h3.ipc-title__text").text();
        String link = listItem.select("a.ipc-title-link-wrapper").attr("href");
        String year = listItem.select("span.cli-title-metadata-item").first().text();
        String rating = listItem.select("span.ipc-rating-star").attr("aria-label").replace("IMDb rating: ", "");

        return new MovieInfo(title, extractRoute(link), year, rating);
    }


    public MovieInfo getMovieInfoByUrl(String route) throws IOException {

        var htmlString = getMoviePageResponse(route).string();
        var document = Jsoup.parse(htmlString);

        String title = document.select("h1 > span.hero__primary-text").text();
        String year = document.select("li.ipc-inline-list__item a[href*=releaseinfo]").first().text();
        String rating = document.select("div[data-testid=hero-rating-bar__aggregate-rating__score]").first().text();

        return new MovieInfo(title, route, year, extractRating(rating));
    }

    private static String extractRating(String inputString) {
        // Find the index of the first occurrence of "?"
        int indexOfQuestionMark = inputString.indexOf('/');

        // If "?" is found, extract the substring up to that index
        if (indexOfQuestionMark != -1) {
            return inputString.substring(0, indexOfQuestionMark);
        } else {
            // If "?" is not found, return the original string
            return inputString;
        }
    }

    private static String extractRoute(String inputString) {
        // Find the index of the first occurrence of "?"
        int indexOfQuestionMark = inputString.indexOf('?');

        // If "?" is found, extract the substring up to that index
        if (indexOfQuestionMark != -1) {
            return inputString.substring(0, indexOfQuestionMark);
        } else {
            // If "?" is not found, return the original string
            return inputString;
        }
    }

}
