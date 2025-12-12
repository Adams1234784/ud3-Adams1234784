// src/main/java/cs112/ud3/Monster.java
package cs112.ud3;

import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Character {
    private final double aggression; // 0.0–1.0 influences variability

    public Monster(String name, int maxHealth, int attack, int defense, double aggression) {
        super(name, maxHealth, attack, defense);
        this.aggression = Math.max(0.0, Math.min(1.0, aggression));
    }

    @Override
    public int attack(Character target) throws BattleException {
        if (isDefeated()) throw new BattleException(getName() + " is defeated and cannot attack.");
        int variability = (int) Math.round(getAttackStat() * (0.25 * aggression));
        int roll = ThreadLocalRandom.current().nextInt(-variability, variability + 1);
        int raw = Math.max(1, getAttackStat() + roll);
        return target.takeDamage(raw);
    }
}
