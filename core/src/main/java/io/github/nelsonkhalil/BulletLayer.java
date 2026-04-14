package io.github.nelsonkhalil;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class BulletLayer {
    private List<Bullet> bullets;
    private Texture bulletSprite;

    public BulletLayer() {
        bullets = new ArrayList<>();
        bulletSprite = new Texture("bullet.png");
    }

    public void addBullet(Vector2 position) {
        bullets.add(new Bullet(position, bulletSprite));
    }

    public void update(float dt) {
        bullets.removeIf(Bullet::shouldRemove);
        for (Bullet bullet : bullets) {
            bullet.update(dt);
        }
    }

    public void render(SpriteBatch batch) {
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
    }

    public void dispose() {
        bulletSprite.dispose();
    }
}
