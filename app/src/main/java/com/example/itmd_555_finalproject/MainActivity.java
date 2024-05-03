package com.example.itmd_555_finalproject;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itmd_555_finalproject.CustomAdapters.PhoneticWordAdapter;
import com.example.itmd_555_finalproject.CustomAdapters.wordMeaningAdapter;
import com.example.itmd_555_finalproject.DictionaryModels.DictionaryAPIResponse;
import com.example.itmd_555_finalproject.ProfileAuthentication.LoginActivity;

public class MainActivity extends AppCompatActivity {


    SearchView searchView;
    TextView textView_word;

    RecyclerView recycler_wordPhonetics ,recycler_wordMeanings;
    ProgressDialog progressDialog;

    PhoneticWordAdapter PhoneticWordAdapter;
    wordMeaningAdapter wordMeaningAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recycler_wordMeanings = findViewById(R.id.recycler_wordMeanings);
//        wordMeaningAdapter = new wordMeaningAdapter(this, null);
//        recycler_wordMeanings.setAdapter(wordMeaningAdapter);


        recycler_wordPhonetics = findViewById(R.id.recycler_wordPhonetics);
//        PhoneticWordAdapter = new PhoneticWordAdapter(this, null);
//        recycler_wordPhonetics.setAdapter(PhoneticWordAdapter);





        progressDialog = new ProgressDialog(this);


        progressDialog.setTitle("Loading...");
        progressDialog.show();


        RequestManager manager = new RequestManager(MainActivity.this);
        manager.fetchWordMeaning(listener, "hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.fetchWordMeaning(listener, query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

    }

    private final onFetchDataListener listener = new onFetchDataListener() {
        @Override
        public void onFetchData(DictionaryAPIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse==null){
                Toast.makeText(MainActivity.this, "No data found!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
            Log.d("API_MESSAGE", apiResponse.toString());

        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(DictionaryAPIResponse apiResponse) {
        textView_word.setText("Word: " + apiResponse.getWord());

        recycler_wordPhonetics.setHasFixedSize(true);
        recycler_wordPhonetics.setLayoutManager(new GridLayoutManager(this, 1));
        PhoneticWordAdapter= new PhoneticWordAdapter(this, apiResponse.getWordPhonetic());
        recycler_wordPhonetics.setAdapter(PhoneticWordAdapter);

        recycler_wordMeanings.setHasFixedSize(true);
        recycler_wordMeanings.setLayoutManager(new GridLayoutManager(this, 1));
        wordMeaningAdapter = new wordMeaningAdapter(this, apiResponse.getWordmeanings());
        recycler_wordMeanings.setAdapter(wordMeaningAdapter);
    }

}