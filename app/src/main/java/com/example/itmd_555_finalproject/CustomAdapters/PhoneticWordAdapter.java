package com.example.itmd_555_finalproject.CustomAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itmd_555_finalproject.DictionaryModels.wordPhonetics;
import com.example.itmd_555_finalproject.R;
import com.example.itmd_555_finalproject.ViewHolders.PhoneticWordViewHolder;

import java.util.List;

public class PhoneticWordAdapter extends RecyclerView.Adapter<PhoneticWordViewHolder> {

    private Context context;
    private List<wordPhonetics> phoneticsList;

    public PhoneticWordAdapter(Context context, List<wordPhonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }


    @NonNull
    @Override
    public PhoneticWordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneticWordViewHolder(LayoutInflater.from(context).inflate(R.layout.phonetic_layout, parent, false));
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull PhoneticWordViewHolder holder, int position) {

        holder.textView_phoneticWord.setText(phoneticsList.get(position).getText());
        holder.image_audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer player = new MediaPlayer();
                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(phoneticsList.get(position).getAudio());
                    player.prepare();
                    player.start();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release(); // Release MediaPlayer when playback completes
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Couldn't play audio!", Toast.LENGTH_SHORT).show();
                    player.release(); // Release MediaPlayer in case of an exception
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return phoneticsList != null ? phoneticsList.size() : 0;
    }
}
