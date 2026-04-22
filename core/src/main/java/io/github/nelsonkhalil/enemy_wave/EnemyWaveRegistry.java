package io.github.nelsonkhalil.enemy_wave;

import io.github.nelsonkhalil.Main;
import io.github.nelsonkhalil.entity.enemy_ship.behaviour.util.EnemyShipBehaviourFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class EnemyWaveRegistry {
    private EnemyWaveRegistry() {}

    public static List<Supplier<EnemyWave>> registry = new ArrayList<>();

    public static Supplier<EnemyWave> WAVE_1 = reg(
        () -> new EnemyWave.Builder()
            .addShip(Main.VIEW_WIDTH / 5 * 1, EnemyShipBehaviourFactory.Type.SINE_WAVE)
            .addShip(Main.VIEW_WIDTH / 5 * 4, EnemyShipBehaviourFactory.Type.SINE_WAVE)
            .addDelaySec(3)
            .addShip(Main.VIEW_WIDTH / 2, EnemyShipBehaviourFactory.Type.AFRAID)
            .build()
    );

    public static Supplier<EnemyWave> WAVE_2 = reg(
        () -> new EnemyWave.Builder()
            .addShip(Main.VIEW_WIDTH / 5 * 1, EnemyShipBehaviourFactory.Type.BASIC)
            .addShip(Main.VIEW_WIDTH / 5 * 4, EnemyShipBehaviourFactory.Type.BASIC)
            .addDelaySec(3)
            .addShip(Main.VIEW_WIDTH / 5 * 2, EnemyShipBehaviourFactory.Type.SINE_WAVE)
            .addShip(Main.VIEW_WIDTH / 5 * 3, EnemyShipBehaviourFactory.Type.SINE_WAVE)
            .addDelaySec(8)
            .addShip(Main.VIEW_WIDTH / 2, EnemyShipBehaviourFactory.Type.KAMIKAZE)
            .build()
    );

    public static Supplier<EnemyWave> WAVE_3 = reg(
        () -> new EnemyWave.Builder()
            .addShip(Main.VIEW_WIDTH / 4 * 1, EnemyShipBehaviourFactory.Type.BASIC)
            .addShip(Main.VIEW_WIDTH / 4 * 3, EnemyShipBehaviourFactory.Type.BASIC)
            .addDelaySec(5)
            .addShip(Main.VIEW_WIDTH / 4 * 2, EnemyShipBehaviourFactory.Type.AFRAID)
            .addDelaySec(3)
            .addShip(Main.VIEW_WIDTH / 4 * 2, EnemyShipBehaviourFactory.Type.AFRAID)
            .addDelaySec(3)
            .addShip(Main.VIEW_WIDTH / 4 * 2, EnemyShipBehaviourFactory.Type.AFRAID)
            .build()
    );

    private static Supplier<EnemyWave> reg(Supplier<EnemyWave> waveSupplier) {
        registry.add(waveSupplier);
        return waveSupplier;
    }
}
