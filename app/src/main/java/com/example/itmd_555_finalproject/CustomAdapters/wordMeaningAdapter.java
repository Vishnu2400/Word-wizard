package com.example.itmd_555_finalproject.CustomAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itmd_555_finalproject.DictionaryModels.DictionaryAPIResponse;
import com.example.itmd_555_finalproject.DictionaryModels.wordMeanings;
import com.example.itmd_555_finalproject.R;
import com.example.itmd_555_finalproject.RequestManager;
import com.example.itmd_555_finalproject.ViewHolders.wordMeaningViewHolder;
import com.example.itmd_555_finalproject.onFetchDataListener;

import java.util.ArrayList;
import java.util.List;

public class wordMeaningAdapter extends RecyclerView.Adapter<wordMeaningViewHolder> {

    private Context context;
    protected List<wordMeanings> wordMeaningsList;

    private String word;


    public wordMeaningAdapter(Context context, List<wordMeanings> wordMeaningsList) {
        this.context = context;
        this.wordMeaningsList = wordMeaningsList;
    }

    @NonNull
    @Override
    public wordMeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new wordMeaningViewHolder(LayoutInflater.from(context).inflate(R.layout.meaning_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull wordMeaningViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView_wordPartsOfSpeech.setText("Parts of speech for the word " + wordMeaningsList.get(position).getPartOfSpeech());
        holder.recycler_WordDefinitions.setHasFixedSize(true);
        holder.recycler_WordDefinitions.setLayoutManager(new GridLayoutManager(context, 1));
        wordDefinationAdapter wordDefinationAdapter = new wordDefinationAdapter(context, wordMeaningsList.get(position).getDefinitions());
        holder.recycler_WordDefinitions.setAdapter(wordDefinationAdapter);

        // Check if the part of speech is an adjective
        if (wordMeaningsList.get(position).getPartOfSpeech().equalsIgnoreCase("adjective")) {

            // Fetch synonyms for adjectives
            RequestManager requestManager = new RequestManager(context);
            requestManager.fetchWordMeaning(new onFetchDataListener() {
                @Override
                public void onFetchData(DictionaryAPIResponse response, String message) {
                    word = response.getWord();
                    // Retrieve synonyms and update the UI
                    if (response != null) {
                        List<wordMeanings> adjectiveMeanings = getMeaningsByPartOfSpeech(response, "adjective");
                        if (!adjectiveMeanings.isEmpty()) {
                            List<String> synonyms = adjectiveMeanings.get(0).getSynonyms();
                            List<String> antonyms = adjectiveMeanings.get(0).getAntonyms();
                            if (!synonyms.isEmpty()) {
                                holder.textView_wordSynonyms.setText(wordMeaningsList.get(position).getSynonyms().toString());
                                holder.textView_wordSynonymsHeading.setVisibility(View.VISIBLE);

                            } else {
                                holder.textView_wordSynonyms.setVisibility(View.GONE);
                                holder.textView_wordSynonymsHeading.setVisibility(View.GONE);
                            }
                            // Update UI with antonyms if not empty
                            if (!antonyms.isEmpty()) {
                                holder.textView_wordAntonyms.setText(wordMeaningsList.get(position).getAntonyms().toString());
                                holder.textView_wordAntonymsHeading.setVisibility(View.VISIBLE);
                            } else {
                                holder.textView_wordAntonyms.setVisibility(View.GONE);
                                holder.textView_wordAntonymsHeading.setVisibility(View.GONE);
                            }
                        }
                    }
                }


                public void onError(String error) {
                    Log.e("RequestManager", "Error: " + error);
                }
            }, word);
        } else {
            // Hide TextViews for synonyms and antonyms if the part of speech is not an adjective
            holder.textView_wordSynonyms.setVisibility(View.GONE);
            holder.textView_wordAntonyms.setVisibility(View.GONE);
        }

        // Set antonyms
        holder.textView_wordAntonyms.setText(wordMeaningsList.get(position).getAntonyms().toString());
        holder.textView_wordSynonyms.setText(wordMeaningsList.get(position).getSynonyms().toString());
        holder.textView_wordSynonyms.setSelected(true);
        holder.textView_wordAntonyms.setSelected(true);
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
    public int getItemCount() {

        if (wordMeaningsList != null) {
            return wordMeaningsList.size();
        } else {
            return 0; // Or return a default size if the list is null
        }
    }
}
