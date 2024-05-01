package com.example.itmd_555_finalproject.CustomAdapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itmd_555_finalproject.R;
import com.example.itmd_555_finalproject.ViewHolders.wordDefinationViewHolder;
import com.example.itmd_555_finalproject.DictionaryModels.wordDefinations;

import java.util.List;


public class wordDefinationAdapter extends RecyclerView.Adapter<wordDefinationViewHolder> {

    private Context context;
    private List<wordDefinations> wordDefinitionsList;

    public wordDefinationAdapter(Context context, List<wordDefinations> wordDefinitionsList) {
        this.context = context;
        this.wordDefinitionsList = wordDefinitionsList;
    }

    @NonNull
    @Override
    public wordDefinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new wordDefinationViewHolder((LayoutInflater.from(context).inflate(R.layout.defination_layout, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull wordDefinationViewHolder holder, int position) {
        holder.textView_wordDefinition.setText("Defination :"+wordDefinitionsList.get(position).getDefinition());
        holder.textView_wordExample.setText("Example word:"+wordDefinitionsList.get(position).getExample());
        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(wordDefinitionsList.get(position).getSynonyms());
        antonyms.append(wordDefinitionsList.get(position).getAntonyms());

        Log.d("definations",wordDefinitionsList.toArray().toString());

        holder.textView_wordSynonyms.setText(synonyms);
        holder.textView_wordAntonyms.setText(antonyms);

        holder.textView_wordSynonyms.setSelected(true);
        holder.textView_wordAntonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {

        if (wordDefinitionsList != null) {
            return wordDefinitionsList.size();
        } else {
            return 0;
        }
    }
}
