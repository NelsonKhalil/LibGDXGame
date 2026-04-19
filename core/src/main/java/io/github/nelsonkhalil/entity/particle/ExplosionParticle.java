package io.github.nelsonkhalil.entity.particle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.World;
import io.github.nelsonkhalil.assetmanager.AssetLoader;
import io.github.nelsonkhalil.assetmanager.MultiFileTexture;
import io.github.nelsonkhalil.render.DrawContext;
import io.github.nelsonkhalil.state.GameState;

public class ExplosionParticle implements Particle {

    private static final float MAX_LIFE = 1.4F;
    private static final float MIN_LIFE = 0.8F;

    private final GeneralParticleInfo ginfo;
    private final ExplosionParticleInfo info;

    private final Sprite sprite;

    public ExplosionParticle(PartialGeneralParticleInfo ginfo, ExplosionParticleInfo info, AssetLoader al) {
        this.ginfo = ginfo.withRandomLife(MIN_LIFE, MAX_LIFE);
        this.info = info;
        this.sprite = new Sprite(al.getTexture(MultiFileTexture.EXPLOSION.getRandom()));
    }

    @Override
    public void update(float dt, World.WorldContext context, AssetLoader al, GameState gameState) {
        ginfo.update(dt);

        sprite.rotate(dt * 25);
        sprite.setScale(ginfo.life / MAX_LIFE * info.size);
    }

    @Override
    public void render(DrawContext context) {
        ginfo.render(sprite, context);
    }

    @Override
    public boolean shouldRemove() {
        return ginfo.lifeZero();
    }

    @Override
    public void dispose() {

    }

    @Override
    public Vector2 getPosition() {
        return ginfo.cpyPos();
    }

}
