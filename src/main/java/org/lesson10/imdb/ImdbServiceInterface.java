package org.lesson10.imdb;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ImdbServiceInterface {

    @GET("/chart/top")
    @Headers({"user-agent: Mozilla/5.0"})
    Call<ResponseBody> getTopChart();


    @GET("{route}")
    @Headers({"user-agent: Mozilla/5.0"})
    Call<ResponseBody> getMoviePage(@Path(value = "route", encoded = true) String route);
}
