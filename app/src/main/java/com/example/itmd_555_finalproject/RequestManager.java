package com.example.itmd_555_finalproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.itmd_555_finalproject.DictionaryModels.DictionaryAPIResponse;
import com.example.itmd_555_finalproject.DictionaryModels.wordDefinations;
import com.example.itmd_555_finalproject.DictionaryModels.wordMeanings;

import java.util.ArrayList;
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
                    Log.d("statuscode", String.valueOf(response.code()));
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<DictionaryAPIResponse> meanings = response.body();
                    if (meanings != null && meanings.size() > 0) {
                        DictionaryAPIResponse wordMeanings = meanings.get(0);
                        Log.d("RequestManager", "Meaning: " + wordMeanings.toString());

                        // Retrieve word meanings
                        List<wordMeanings> wordMeaningsList = getMeaningsByPartOfSpeech(wordMeanings, "adjective");
                        if (wordMeaningsList != null && !wordMeaningsList.isEmpty()) {
                            for (wordMeanings Posmeanings : wordMeaningsList) {
                                String partOfSpeech = Posmeanings.getPartOfSpeech();
                                Log.d("Part of Speech", partOfSpeech);

                                // Retrieve definitions for each part of speech
                                List<wordDefinations> definitions = Posmeanings.getDefinitions();
                                if (definitions != null && !definitions.isEmpty()) {
                                    for (wordDefinations definition : definitions) {
                                        List<String> synonyms = definition.getSynonyms();
                                        List<String> antonyms = definition.getAntonyms();

                                        // Now you can use synonyms and antonyms lists
                                        Log.d("Synonyms", synonyms.toString());
                                        Log.d("Antonyms", antonyms.toString());
                                    }
                                }
                            }
                        } else {
                            Log.e("RequestManager", "No word meanings found");
                            Toast.makeText(context, "No word meanings found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("RequestManager", "No meaning found");
                        Toast.makeText(context, "No meaning found", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().get(0), response.message());
                }


                private List<wordMeanings> getMeaningsByPartOfSpeech(DictionaryAPIResponse response, String partOfSpeech) {
                    List<wordMeanings> meanings = new ArrayList<>();
                    if (response != null && response.getWordmeanings() != null) {
                        for (wordMeanings meaning : response.getWordmeanings()) {
                            if (meaning.getPartOfSpeech().equalsIgnoreCase(partOfSpeech)) {
                                meanings.add(meaning);
                            }
                        }
                    }
                    return meanings;
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
