package com.example.itmd_555_finalproject;

import android.content.Context;
import android.widget.Toast;

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
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {

        this.context = context;
    }

    public void fetchWordMeaning(onFetchDataListener listener, String word){

          CallAPIDictionaryResp callAPIDictionaryResp = retrofit.create(CallAPIDictionaryResp.class);
          Call<List<DictionaryAPIResponse>> call = callAPIDictionaryResp.callMeanings(word);

        try{
            call.enqueue(new Callback<List<DictionaryAPIResponse>>() {

                @Override
                public void onResponse(Call<List<DictionaryAPIResponse>> call, Response<List<DictionaryAPIResponse>> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    listener.onFetchData(response.body().get(0), response.message());

                }

                @Override
                public void onFailure(Call<List<DictionaryAPIResponse>> call, Throwable t) {
                    listener.onError("Failed to Fetch the word!!");
                }
            });
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "An Error Occurred!!!", Toast.LENGTH_SHORT).show();
        }


    }

    public interface CallAPIDictionaryResp {
        @GET("entries/en/{word}")
        Call<List<DictionaryAPIResponse>> callMeanings(
                @Path("word") String word
        );

    }
}
