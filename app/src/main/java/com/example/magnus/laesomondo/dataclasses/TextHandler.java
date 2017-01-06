package com.example.magnus.laesomondo.dataclasses;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Magnus on 06-01-2017.
 */

public class TextHandler {

    private String text;
    private int time;
    private int difficulty;

    public TextHandler() {

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

            BufferedReader reader = new BufferedReader(new InputStreamReader(f, "windows-1252"));

            String temp;
            while ((temp = reader.readLine()) != null) {
                toRead.append(temp).append('\n');
            }

            total = toRead.toString();
            int wordsToBring = time*difficulty;

            String[] splitted = total.split(" ");
            int amountWords = splitted.length; //.split("\\s+") might be better here.

            if(wordsToBring < amountWords) {
                //splitted = Arrays.copyOfRange(splitted, 0, wordsToBring);
                total = "";
                for(int i = 0; i <= wordsToBring; i++){

                    total += splitted[i];
                    total += " ";

                    while( i >= wordsToBring && i <= amountWords){
                        if(i  >= wordsToBring){
                            i++;
                            total += splitted[i];
                            if(splitted[i].contains(".")){
                                return total;
                            }
                            total += " ";
                        }
                    }

                }
                return total;
            }
            else if (wordsToBring > amountWords){

                return total;



            }


        }
        catch (IOException io){
            io.printStackTrace();
        }




        return total;
    }
}
