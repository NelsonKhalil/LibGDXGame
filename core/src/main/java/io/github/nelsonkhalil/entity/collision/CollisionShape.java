package io.github.nelsonkhalil.entity.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.render.DrawContext;

public class CollisionShape {

    public final Circle circle;

    public CollisionShape(Circle circle) {
        this.circle = circle;
    }

    public CollisionShape(Vector2 position, float radius) {
        this(new Circle(position, radius));
    }

    public boolean intersects(CollisionShape other) {
        return circle.overlaps(other.circle);
    }

    public boolean isOnScreen() {
        float half = circle.radius;
        if ((circle.x + half) < 0 || (circle.x - half) > Main.VIEW_WIDTH)
            return false;
        if ((circle.y + half) < 0 || (circle.y - half) > Main.VIEW_HEIGHT)
            return false;

        return true;
    }

    public void renderDebug(DrawContext context) {
        if (!context.DRAW_DEBUG_HITBOX) return;
        context.shapeRenderer.circle(circle.x, circle.y, circle.radius);
    }
}
