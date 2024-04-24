package com.example.itmd_555_finalproject.DictionaryModels;

import java.util.List;

public class DictionaryAPIResponse {

    String word = "";

    List<wordPhonetics> phonetics = null;

    List<wordMeanings> meanings = null;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<wordPhonetics> getWordPhonetic() {

        return phonetics;
    }

    public void setWordPhonetic(List<wordPhonetics> phonetics) {
        this.phonetics = phonetics;
    }

    public List<wordMeanings> getWordmeanings() {

        return meanings;
    }

    public void setWordmeanings(List<wordMeanings> meanings) {
        this.meanings = meanings;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word: ").append(word).append("\n");
        if (meanings != null) {
            sb.append("Meanings:\n");
            for (wordMeanings meaning : meanings) {
                sb.append(meaning.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
