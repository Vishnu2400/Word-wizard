package com.example.itmd_555_finalproject.DictionaryModels;

import java.util.List;

public class DictionaryAPIResponse {

    String word = "";

    List<wordPhonetics> wordPhonetic = null;

    List<wordMeanings> wordmeanings = null;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<wordPhonetics> getWordPhonetic() {
        return wordPhonetic;
    }

    public void setWordPhonetic(List<wordPhonetics> wordPhonetic) {
        this.wordPhonetic = wordPhonetic;
    }

    public List<wordMeanings> getWordmeanings() {
        return wordmeanings;
    }

    public void setWordmeanings(List<wordMeanings> wordmeanings) {
        this.wordmeanings = wordmeanings;
    }
}
