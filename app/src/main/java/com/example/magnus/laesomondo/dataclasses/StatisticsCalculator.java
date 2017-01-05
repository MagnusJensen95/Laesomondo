package com.example.magnus.laesomondo.dataclasses;

/**
 * Created by Magnus on 03-01-2017.
 */

public class StatisticsCalculator {

    //globale gennemsnitlige læsehastighed (ord/minut).
    private final int globalAverageTimeToRead = 200;

    //brugerens gennemsnitlige læsehastighed (ord/minut).
    private int userTimeToRead;

    //user/global reading time ratio.
    private static double ratio;

    public static double[] calculateComparativeReadingTime(double userTimeToRead, int amountWords){

        //ratio is used for summary pop up, as prompt to user.
        ratio = (amountWords / userTimeToRead) / (200 / 60);
        //globaltextreadtime is used for comparison in graph in user profile.
        double globalCurrentTextReadTime = (200 / 60) * ratio;

        double[] toReturn = {ratio, globalCurrentTextReadTime};
        return toReturn;
    }
}
