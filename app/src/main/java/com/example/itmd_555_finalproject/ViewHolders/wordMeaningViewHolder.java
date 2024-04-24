package com.example.itmd_555_finalproject.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itmd_555_finalproject.R;

public class wordMeaningViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView recycler_WordDefinitions;
    public TextView textView_wordPartsOfSpeech;

    public wordMeaningViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_wordPartsOfSpeech = itemView.findViewById(R.id.textView_wordPartsOfSpeech);
        recycler_WordDefinitions = itemView.findViewById(R.id.recycler_WordDefinitions);
    }
}
