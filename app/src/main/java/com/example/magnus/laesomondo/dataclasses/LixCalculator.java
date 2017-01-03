package com.example.magnus.laesomondo.dataclasses;

import android.util.Log;

/**
 * Created by Magnus on 03-01-2017.
 */
        /*var text0 = document.getElementById("text").value;
		var text = text0.replace(/[:;][-]?[()podPOD]/g, "")
		var text1 = text.replace(/[\?!;:]/g, ".") //spørgsmåltegn, udråbsytegn etc erstattes med punktummer
		var text2 = text1.replace(/[\.,()"'!;\n\r]/g, " ") //tegn fjernes fra strengen, for at de ikke skal tælles som ekstra bogstaver, og dermed give flere lange ord.
		var a1 = text1.split(".");
		var punktummer = a1.length - 1;

		var a2 = text2.split(" ");
		var ord1 = a2.length;
		var langeord = 0;
		var mellemrum = 0;

		for(var i=0; i<ord1; i++){
			if (a2[i].length <1){ //2 på hinanden følgende mellemrum vil optræder i array som elementer med længeden 0. Disse mellemrum tælles for at kunne finde det reeele antal ord.
				mellemrum++;
			}
			if (a2[i].length > 6){
				langeord++;
			}
		}

		var ord2 = ord1 - mellemrum;
		if(ord2 != 0 && punktummer != 0){
			var lix = Math.round(ord2/punktummer + (langeord*100)/ord2);*/



public class LixCalculator {
    public static int calcLix(String text){

        //Finding amount of words in text.
        String temptemp = text.replaceAll("[\\W]"," ");

        Log.i(String.valueOf(temptemp), "LixNumber");

        int amountWords = temptemp.split(" ").length;
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