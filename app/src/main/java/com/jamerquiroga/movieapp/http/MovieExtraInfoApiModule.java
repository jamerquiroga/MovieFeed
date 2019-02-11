package com.jamerquiroga.movieapp.http;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieExtraInfoApiModule {

    public static final String BASE_URL = "http://www.omdbapi.com";

    public static final String API_KEY = "319f8457";

    /**
     * @return obtenemos el BODY de la llamada
     */
    @Provides
    public OkHttpClient provideClient(){
        HttpLoggingInterceptor oHttpLoggingInterceptor = new HttpLoggingInterceptor();
        oHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(oHttpLoggingInterceptor).build();
    }

    /**
     * Utilizamos "Gson" como libreria de deserializacion
     * @param psBaseUrl
     * @param poClient
     * @return
     */
    @Provides
    public Retrofit provideRetrofit(String psBaseUrl, OkHttpClient poClient){
        return new Retrofit.Builder()
                .baseUrl(psBaseUrl)
                .client(poClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public MoviesExtraInfoApiService provideApiService(){
        return provideRetrofit(BASE_URL, provideClient()).create(MoviesExtraInfoApiService.class);
    }

}
