package com.example.magnus.laesomondo.dataclasses;

import android.util.Log;

/**
 * Created by Magnus on 03-01-2017.
 */

public class LixCalculator {
    public static int calcLix(String text){

        //Finding amount of words in text.
        String temptemp = text.replaceAll("[\\W]"," ");

        int amountWords = temptemp.split(" ").length; //.split("\\s+") might be better here.
        /*for(int i = 0; i < text.length(); i++){
            if(temptemp.charAt(i)==' '){
                amountWords++;
            }
        }*/

        //Finding amount of periods in text.
        int amountPeriods = 0;
        for( int i = 0; i < text.length(); i++ ){
            if(text.charAt(i)=='.')
                amountPeriods++;
        }

        //Finding amount of long words in text (length < 6).
        String[] temp = text.split(" ");
        int amountLongWords = 0;
        for(int i = 0; i < temp.length; i++){
            if(temp[i].length() > 6){
                amountLongWords++;
            }
        }

        //Calculating and returning LIX Number.
        int LixNumber = (amountWords / amountPeriods) + ( (amountLongWords * 100) / amountWords );

        Log.i(String.valueOf(LixNumber), "LixNumber");
        Log.i(String.valueOf(amountWords), "O");
        Log.i(String.valueOf(amountPeriods), "P");
        Log.i(String.valueOf(amountLongWords), "L");

        return LixNumber;
    }
}