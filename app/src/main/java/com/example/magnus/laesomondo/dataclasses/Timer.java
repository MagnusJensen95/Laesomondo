package com.example.magnus.laesomondo.dataclasses;

/**
 * Created by buller on 16/01/2017.
 */
public class Timer {

    private static long timer = 0;
    private static long timeSpentPaused = 0;

    public Timer(){
        timer = 0;
    }

    public void start(){
        if(timer==0){
            timer = System.currentTimeMillis();
        }else{
            timeSpentPaused = System.currentTimeMillis() - timeSpentPaused;
        }
    }

    public void pause(){
        timeSpentPaused = System.currentTimeMillis();
    }

    public void reset(){
        timer = 0;
        timeSpentPaused = 0;
    }

    public long getTimeToPrint(){
        return timer-(System.currentTimeMillis()-timeSpentPaused);
    }
}
