package com.example.itmd_555_finalproject.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itmd_555_finalproject.R;

public class PhoneticWordViewHolder extends RecyclerView.ViewHolder {

    public TextView textView_phoneticWord;
    public ImageButton image_audioButton;


    public PhoneticWordViewHolder(@NonNull View itemView) {
        super(itemView);

        image_audioButton = itemView.findViewById(R.id.image_audioButton);
        textView_phoneticWord = itemView.findViewById(R.id.textView_phoneticWord);


    }
}
