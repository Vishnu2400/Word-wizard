package com.example.itmd_555_finalproject;

import com.example.itmd_555_finalproject.DictionaryModels.DictionaryAPIResponse;

public interface onFetchDataListener {

    void onFetchData(DictionaryAPIResponse apiResponse, String message);
    void onError(String message);


}
