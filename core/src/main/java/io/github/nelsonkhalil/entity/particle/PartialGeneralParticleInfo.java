package io.github.nelsonkhalil.entity.particle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.helper.VectorHelper;
import io.github.nelsonkhalil.render.DrawContext;

public class PartialGeneralParticleInfo {
    public Vector2 position;
    public Vector2 velocity;

    public PartialGeneralParticleInfo(Vector2 position, Vector2 velocity) {
        this.position = position.cpy();
        this.velocity = velocity.cpy();
    }

    public PartialGeneralParticleInfo(Vector2 position) {
        this(position, Vector2.Zero);
    }

    public PartialGeneralParticleInfo withRandomVel(float length) {
        this.velocity = VectorHelper.randomVector(length);
        return this;
    }

    public GeneralParticleInfo withLife(float life) {
        return new GeneralParticleInfo(this, life);
    }

    public GeneralParticleInfo withRandomLife(float min, float max) {
        return withLife(MathUtils.random(min, max));
    }

    public void update(float dt, float velocityMultiplier) {
        velocity.scl((float) Math.pow(velocityMultiplier, dt)); // Exponential decay
        position.add(velocity.cpy().scl(dt));
    }

    public void render(Sprite sprite, DrawContext context) {
        sprite.setCenter(position.x, position.y);
        sprite.setOriginCenter();
        sprite.draw(context.batch);
    }

    public Vector2 cpyPos() {
        return position.cpy();
    }

}
