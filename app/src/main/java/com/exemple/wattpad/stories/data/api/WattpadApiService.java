package com.exemple.wattpad.stories.data.api;

import com.exemple.wattpad.stories.BuildConfig;
import com.exemple.wattpad.stories.data.repositories.responses.StoriesResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public interface WattpadApiService {

    String OFFSET = "offset";
    String LIMIT = "limit";
    String FIELDS = "fields";

    @GET("/api/v3/stories")
    Observable<StoriesResponse> getStories(@Query(OFFSET) int offset, @Query(LIMIT) int limit,
                                           @Query(FIELDS) String fields);

    class Builder {

        public static WattpadApiService build() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_HOST)
                    .addConverterFactory(GsonConverterFactory.create(createGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createClient())
                    .build();
            return retrofit.create(WattpadApiService.class);
        }

        private static OkHttpClient createClient() {
            //adding logging to client
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            return new OkHttpClient.Builder().addInterceptor(logging).build();
        }

        private static Gson createGson() {
            return new GsonBuilder()
                    .setLenient()
                    .create();
        }

    }

}
