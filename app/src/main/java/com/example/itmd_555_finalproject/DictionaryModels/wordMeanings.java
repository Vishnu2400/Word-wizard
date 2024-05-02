package com.example.itmd_555_finalproject.DictionaryModels;

import java.util.List;

public class wordMeanings {

    String partOfSpeech = "";

    List<String> synonyms = null;
    List<String> antonyms = null;

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<String> antonyms) {
        this.antonyms = antonyms;
    }

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
