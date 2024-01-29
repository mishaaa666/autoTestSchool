package org.lesson10.tests;

import org.lesson10.imdb.ImdbApi;
import org.lesson10.imdb.MovieInfo;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WebApiTests {
    private final ImdbApi imdbApi = new ImdbApi();

    private static Object[][] convertListToArray(List<MovieInfo> movieInfoList) {
        int size = movieInfoList.size();
        Object[][] movieInfoArray = new Object[size][1];

        for (int i = 0; i < size; i++) {
            movieInfoArray[i][0] = movieInfoList.get(i);
        }

        return movieInfoArray;
    }

    @DataProvider(name = "moviesListDataProvider")
    public Object[][] chartDataProvider() throws IOException {
        var limit = 5;
        var array = convertListToArray(imdbApi.getTop100Films());

        return Arrays.copyOfRange(array, 0, limit);
    }

    @Test
    public void imdbTop100ChartTest() throws IOException {
        for (MovieInfo movie : imdbApi.getTop100Films()) {
            System.out.println("Title: " + movie.title());
            System.out.println("Link: " + movie.link());
            System.out.println("Year: " + movie.year());
            System.out.println("Rating: " + movie.rating());
            System.out.println();
        }
    }

    @Test
    public void imdbDetailPageTest() throws IOException {
        var movieInfo = imdbApi.getMovieInfoByUrl("/title/tt0068646");
        System.out.println("Title: " + movieInfo.title());
        System.out.println("Link: " + movieInfo.link());
        System.out.println("Year: " + movieInfo.year());
        System.out.println("Rating: " + movieInfo.rating());
        System.out.println();

    }

    @Test(dataProvider = "moviesListDataProvider")
    public void validateTopChartPage(MovieInfo movieInfo) throws IOException {
        var movieInfoFromDetail = imdbApi.getMovieInfoByUrl(movieInfo.link());
        Assert.assertTrue(movieInfo.title().contains(movieInfoFromDetail.title()));
        Assert.assertEquals(movieInfo.year(), movieInfoFromDetail.year());
        Assert.assertEquals(movieInfo.rating(), movieInfoFromDetail.rating());
    }
}