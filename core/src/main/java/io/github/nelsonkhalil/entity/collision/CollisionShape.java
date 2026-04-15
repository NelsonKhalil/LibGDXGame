package io.github.nelsonkhalil.entity.collision;

import com.badlogic.gdx.math.Vector2;
import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.render.DrawContext;

public record CollisionShape(float radius, Vector2 offset) {
    public CollisionShape(float radius) {
        this(radius, Vector2.Zero);
    }

    public boolean intersects(CollisionShape other, Vector2 thisPos, Vector2 otherPos) {
        Vector2 thisOrigin = thisPos.add(this.offset);
        Vector2 otherOrigin = otherPos.add(other.offset);
        float distance = thisOrigin.dst(otherOrigin);
        return (this.radius + other.radius) > distance;
    }

    public boolean isOnScreen(Vector2 thisPos) {
        Vector2 thisOrigin = thisPos.cpy().add(offset);
        float half = radius / 2;

        if ((thisOrigin.x + half) < 0 || (thisOrigin.x - half) > Main.VIEW_WIDTH)
            return false;
        if ((thisOrigin.y + half) < 0 || (thisOrigin.y - half) > Main.VIEW_HEIGHT)
            return false;

        return true;
    }

    public void renderDebug(Vector2 pos, DrawContext context) {
        if (!context.DRAW_DEBUG_HITBOX) return;
        Vector2 origin = pos.add(offset);
        context.shapeRenderer.circle(origin.x, origin.y, radius);
    }
}
