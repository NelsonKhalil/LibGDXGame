package io.github.nelsonkhalil.enemy_wave;

public class LevelLoadExeption extends RuntimeException {
    public LevelLoadExeption(String message) {
        super(message);
    }

    public LevelLoadExeption(Exception e) {
        super(e);
    }
}
