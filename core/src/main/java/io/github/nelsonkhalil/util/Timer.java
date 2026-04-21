package io.github.nelsonkhalil.util;

public class Timer {
    private float time;

    public Timer() {
        time = 0;
    }

    public void setMilli(int milliseconds) {
        setSeconds(milliseconds / 1000F);
    }

    public void setSeconds(float seconds) {
        time = seconds;
    }

    public boolean isDue() {
        return time == 0;
    }

    public void update(float dt) {
        time = Math.max(0, time - dt);
    }
}
