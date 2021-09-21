package com.hadimusthafa.retrofitget;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://api.tvmaze.com/";

    @GET("shows")
    Call<String> getWhatWeNeed(
            @Query("id") String id,
            @Query("another_parameter") String another_parameter
    );

}

