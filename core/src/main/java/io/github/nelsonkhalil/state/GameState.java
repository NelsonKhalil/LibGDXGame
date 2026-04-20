package io.github.nelsonkhalil.state;

public class GameState {
    private static final int MAX_LIVES = 3;

    private int score;
    private int lives;

    public GameState() {
        reset();
    }

    public void reset() {
        score = 0;
        lives = MAX_LIVES;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public void kill() {
        lives = Math.max(0, lives - 1);
    }

    public int getLives() {
        return lives;
    }

    public boolean gameOver() {
        return lives == 0;
    }

    public void addLife() {
        lives = Math.min(lives + 1, MAX_LIVES);
    }
}
