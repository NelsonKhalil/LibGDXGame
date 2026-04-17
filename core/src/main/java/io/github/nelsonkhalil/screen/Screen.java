package io.github.nelsonkhalil.screen;

import com.badlogic.gdx.utils.Disposable;

public interface Screen extends Disposable {
    void update(float dt);
    void render();
}
