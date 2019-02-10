package com.jamerquiroga.movieapp.http;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieTitleApiModule {

    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    public static final String API_KEY = "26f348e74566c02ce43ee53c080bfb03";

    /** Aqui se explica como se puede enviar un API Key de otra forma
     * @return obtenemos el BODY de la llamada
     */
    @Provides
    public OkHttpClient provideClient(){

        HttpLoggingInterceptor loHttpLoggingInterceptor = new HttpLoggingInterceptor();
        loHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .addInterceptor(loHttpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request loRequest = chain.request();
                        HttpUrl loUrl = loRequest.url().newBuilder().addQueryParameter("api_key", API_KEY).build();
                        loRequest = loRequest.newBuilder().url(loUrl).build();
                        return chain.proceed(loRequest);
                    }
                }).build();
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
    public MovieTitleApiService provideApiService(){
        return provideRetrofit(BASE_URL, provideClient()).create(MovieTitleApiService.class);
    }


}
