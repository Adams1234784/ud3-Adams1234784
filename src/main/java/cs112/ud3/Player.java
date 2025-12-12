// src/main/java/cs112/ud3/Player.java
package cs112.ud3;

public class Player extends Character {
    private int healsLeft = 3;
    private int specialCharges = 2;

    public Player(String name, int maxHealth, int attack, int defense) {
        super(name, maxHealth, attack, defense);
    }

    @Override
    public int attack(Character target) throws BattleException {
        if (isDefeated()) throw new BattleException("Player is defeated and cannot attack.");
        int base = getAttackStat();
        int dealt = target.takeDamage(base);
        return dealt;
    }

    public int specialAttack(Character target) throws BattleException {
        if (isDefeated()) throw new BattleException("Player is defeated and cannot use special.");
        if (specialCharges <= 0) throw new BattleException("No special attacks remaining.");
        specialCharges--;
        int base = (int) Math.round(getAttackStat() * 1.75);
        return target.takeDamage(base);
    }

    public int useHeal() throws BattleException {
        if (healsLeft <= 0) throw new BattleException("No heals remaining.");
        healsLeft--;
        int amount = Math.max(10, (int) Math.round(getMaxHealth() * 0.25));
        heal(amount);
        return amount;
    }

    public int getHealsLeft() { return healsLeft; }
    public int getSpecialCharges() { return specialCharges; }
}
