package com.example.itmd_555_finalproject.DictionaryModels;

import java.util.List;

public class wordMeanings {

    String partOfSpeech = "";

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<wordDefinations> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<wordDefinations> definitions) {
        this.definitions = definitions;
    }

    List<wordDefinations> definitions = null;

}
