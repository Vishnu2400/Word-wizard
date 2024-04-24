package com.example.itmd_555_finalproject.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itmd_555_finalproject.DictionaryModels.wordMeanings;
import com.example.itmd_555_finalproject.R;
import com.example.itmd_555_finalproject.ViewHolders.wordMeaningViewHolder;

import java.util.List;

public class wordMeaningAdapter extends RecyclerView.Adapter<wordMeaningViewHolder> {

    private Context context;
    protected List<wordMeanings> wordMeaningsList;

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
    public void onBindViewHolder(@NonNull wordMeaningViewHolder holder, int position) {

        holder.textView_wordPartsOfSpeech.setText("parts of speech for the word "+wordMeaningsList.get(position).getPartOfSpeech());
        holder.recycler_WordDefinitions.setHasFixedSize(true);
        holder.recycler_WordDefinitions.setLayoutManager(new GridLayoutManager(context, 1));
        wordDefinationAdapter wordDefinationAdapter = new wordDefinationAdapter(context, wordMeaningsList.get(position).getDefinitions());
        holder.recycler_WordDefinitions.setAdapter(wordDefinationAdapter);
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
