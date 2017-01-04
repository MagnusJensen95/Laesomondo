package com.example.magnus.laesomondo.dataclasses;

/**
 * Created by Magnus on 10-10-2016.
 */

public class Result {

    private String titel;

    private int lix;

    private int words;

    private double time;
    private double ratio;

    public Result(String titel, int words, double time, int lix, double ratio){

        this.titel = titel;
        this.words = words;
        this.time = time;
        this.lix = lix;
        this.ratio = ratio;
    }

    public int getLix() {
        return lix;
    }


    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public double getRatio() {
        return ratio;
    }
}
