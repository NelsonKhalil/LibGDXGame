package io.github.nelsonkhalil.state;

public class GameState {
    private int score;
    private int lives;

    public GameState() {
        reset();
    }

    public void reset() {
        score = 0;
        lives = 3;
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
}
