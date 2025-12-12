// src/main/java/cs112/ud3/Character.java
package cs112.ud3;

public abstract class Character {
    private final String name;
    private int health;
    private final int maxHealth;
    private final int attack;
    private final int defense;

    public Character(String name, int maxHealth, int attack, int defense) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");
        if (maxHealth <= 0 || attack < 0 || defense < 0) throw new IllegalArgumentException("Invalid stats");
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getAttackStat() { return attack; }
    public int getDefenseStat() { return defense; }

    public boolean isDefeated() {
        return health <= 0;
    }

    public int takeDamage(int incoming) {
        int mitigated = Math.max(0, incoming - defense);
        health = Math.max(0, health - mitigated);
        return mitigated;
    }

    public void heal(int amount) {
        if (amount <= 0) return;
        health = Math.min(maxHealth, health + amount);
    }

    public double getHealthPercentage() {
        return maxHealth == 0 ? 0.0 : (double) health / (double) maxHealth;
    }

    public abstract int attack(Character target) throws BattleException;
}
