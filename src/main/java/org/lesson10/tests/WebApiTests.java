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

    @Test(dataProvider = "moviesListDataProvider")
    public void validateTopChartPage(MovieInfo movieInfo) throws IOException {
        var movieInfoFromDetail = imdbApi.getMovieInfoByUrl(movieInfo.getLink());
        Assert.assertTrue(movieInfo.getTitle().contains(movieInfoFromDetail.getTitle()));
        Assert.assertEquals(movieInfo.getYear(), movieInfoFromDetail.getYear());
        Assert.assertEquals(movieInfo.getRating(), movieInfoFromDetail.getRating());
    }
}