package com.fruits.pro.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by cwj on 16/7/17.
 */
public class HttpClient {
    public static final String HOST = "";
    private static IFruitRetrofit fruitRetrofit;
    protected static final Object monitor = new Object();
    private static Retrofit retrofit;

    private HttpClient(){

    }

    static {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public static IFruitRetrofit getGankRetrofitInstance() {
        synchronized (monitor) {
            if (fruitRetrofit == null) {
                fruitRetrofit = retrofit.create(IFruitRetrofit.class);
            }
            return fruitRetrofit;
        }
    }
}
