package com.example.itmd_555_finalproject.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itmd_555_finalproject.R;

public class wordDefinationViewHolder extends RecyclerView.ViewHolder {

    public TextView  textView_wordExample, textView_wordDefinition;


    public wordDefinationViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_wordDefinition = itemView.findViewById(R.id.textView_wordDefinition);
        textView_wordExample = itemView.findViewById(R.id.textView_wordExample);

    }
}
