package com.example.itmd_555_finalproject;

import android.content.Context;

import com.example.itmd_555_finalproject.DictionaryModels.DictionaryAPIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public interface CallAPIDictionaryResp {
        @GET("entries/en/{word}")
        Call<List<DictionaryAPIResponse>> callMeanings(
                @Path("word") String word
        );

    }
}
