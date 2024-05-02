package com.example.itmd_555_finalproject;

import android.content.Context;
import android.util.Log;

import com.example.itmd_555_finalproject.DictionaryModels.DictionaryAPIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    private Context context;
    private Retrofit retrofit;

    public RequestManager(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.dictionaryapi.dev/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void fetchWordMeaning(onFetchDataListener listener, String word) {
        CallAPIDictionaryResp callAPIDictionaryResp = retrofit.create(CallAPIDictionaryResp.class);
        Call<List<DictionaryAPIResponse>> call = callAPIDictionaryResp.callMeanings(word);

        call.enqueue(new Callback<List<DictionaryAPIResponse>>() {
            @Override
            public void onResponse(Call<List<DictionaryAPIResponse>> call, Response<List<DictionaryAPIResponse>> response) {
                if (response.isSuccessful()) {
                    List<DictionaryAPIResponse> meanings = response.body();
                    if (meanings != null && !meanings.isEmpty()) {
                        // Here, you may want to iterate over meanings if there are multiple meanings in the response
                        DictionaryAPIResponse wordMeanings = meanings.get(0);
                        Log.d("RequestManager", "Meaning: " + wordMeanings.toString());
                        listener.onFetchData(wordMeanings, response.message());
                    } else {
                        Log.e("RequestManager", "No meaning found");
                        listener.onError("No meaning found");
                    }
                } else {
                    Log.e("RequestManager", "Error: " + response.code());
                    listener.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<DictionaryAPIResponse>> call, Throwable t) {
                Log.e("RequestManager", "Failed to Fetch the word: " + t.getMessage());
                listener.onError("Failed to Fetch the word!!");
            }
        });
    }

    public interface CallAPIDictionaryResp {
        @GET("entries/en/{word}")
        Call<List<DictionaryAPIResponse>> callMeanings(
                @Path("word") String word
        );
    }
}
