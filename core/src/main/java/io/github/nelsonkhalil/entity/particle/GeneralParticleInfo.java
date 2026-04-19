package io.github.nelsonkhalil.entity.particle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.render.DrawContext;

public class GeneralParticleInfo {
    public final PartialGeneralParticleInfo info;
    public float life;

    public GeneralParticleInfo(PartialGeneralParticleInfo info, float life) {
        this.info = info;
        this.life = life;
    }

    public void update(float dt, float velocityMultiplier) {
        info.update(dt, velocityMultiplier);
        life = Math.max(0, life - dt);
    }

    public void update(float dt) {
        update(dt, 0.9F);
    }

    public void render(Sprite sprite, DrawContext context) {
        info.render(sprite, context);
    }

    public boolean lifeZero() {
        return life == 0;
    }

    public Vector2 pos() {
        return info.position;
    }

    public Vector2 vel() {
        return info.velocity;
    }

    public Vector2 cpyPos() {
        return info.cpyPos();
    }
}
