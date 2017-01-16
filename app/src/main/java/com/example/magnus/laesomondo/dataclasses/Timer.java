package com.example.magnus.laesomondo.dataclasses;

/**
 * Created by buller on 16/01/2017.
 */
public class Timer {

    private static long timer = 0;
    private static long timeSpentPausedStop = 0;
    private static long timeSpentPausedStart = 0;

    public Timer(){

    }

    public void start(){
        if(timer==0){
            timer = System.currentTimeMillis();
        }else{
            timeSpentPausedStop+=System.currentTimeMillis() - timeSpentPausedStart;
        }
    }

    public void pause(){
        timeSpentPausedStart = System.currentTimeMillis();
    }

    public void reset(){
        timer = 0;
        timeSpentPausedStop = 0;
        timeSpentPausedStart = 0;
    }

    public long getTimeToPrint(){
        return ((System.currentTimeMillis()-timer)-timeSpentPausedStop);
    }
}
