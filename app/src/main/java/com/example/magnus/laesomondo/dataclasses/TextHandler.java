package com.example.magnus.laesomondo.dataclasses;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Magnus on 06-01-2017.
 */

public class TextHandler {

    private String text;
    private int time;
    private int difficulty;

    public TextHandler(int difficulty, String text, int time) {
        this.difficulty = difficulty;
        this.text = text;
        this.time = time;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String retrieveText(Context context, int time, int difficulty, String titel){
        StringBuilder toRead = new StringBuilder();
        String total = "";
        try{
            InputStream f = context.getResources().getAssets().open(titel);
            BufferedReader reader = new BufferedReader(new InputStreamReader(f));
            String temp;
            while ((temp = reader.readLine()) != null) {
                toRead.append(temp).append('\n');
            }

            total = toRead.toString();


        }
        catch (IOException io){
            io.printStackTrace();
        }




        return total;
    }
}
