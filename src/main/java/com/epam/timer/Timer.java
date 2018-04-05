package com.epam.timer;

public class Timer {

    private long start;

    public Timer() {
        this.start = System.nanoTime();
    }

    public double duration() {
        return (double) (System.nanoTime() - this.start) / 1000000000.0;
    }
}
