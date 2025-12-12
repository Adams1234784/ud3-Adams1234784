// src/main/java/cs112/ud3/BattleSystem.java
package cs112.ud3;

import java.util.ArrayList;
import java.util.List;

public class BattleSystem {
    private final Player player;
    private final List<Monster> monsters = new ArrayList<>();
    private Monster currentMonster;
    private int round = 1;
    private final StringBuilder battleLog = new StringBuilder();

    public BattleSystem(Player player) {
        this.player = player;
        generateMonsters();
        currentMonster = monsters.get(0);
        log("A wild " + currentMonster.getName() + " appears!");
    }

    private void generateMonsters() {
        // Progressive difficulty
        monsters.add(new Monster("Slime", 40, 8, 2, 0.3));
        monsters.add(new Monster("Goblin", 60, 12, 4, 0.5));
        monsters.add(new Monster("Orc Warrior", 85, 16, 6, 0.6));
        monsters.add(new Monster("Drake", 120, 22, 8, 0.7));
    }

    public String playerAttack() {
        try {
            int dealt = player.attack(currentMonster);
            String msg = player.getName() + " deals " + dealt + " to " + currentMonster.getName() + ".";
            log(msg);
            advanceIfMonsterDefeated();
            return msg;
        } catch (BattleException e) {
            log("ERROR: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String playerSpecialAttack() {
        try {
            int dealt = player.specialAttack(currentMonster);
            String msg = player.getName() + " unleashes SPECIAL for " + dealt + " on " + currentMonster.getName() + "!";
            log(msg);
            advanceIfMonsterDefeated();
            return msg;
        } catch (BattleException e) {
            log("ERROR: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String playerHeal() {
        try {
            int amount = player.useHeal();
            String msg = player.getName() + " heals " + amount + " HP. Heals left: " + player.getHealsLeft() + ".";
            log(msg);
            return msg;
        } catch (BattleException e) {
            log("ERROR: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String monsterTurn() {
        if (isBattleOver()) return "Battle over.";
        try {
            int dealt = currentMonster.attack(player);
            String msg = currentMonster.getName() + " hits " + player.getName() + " for " + dealt + ".";
            log(msg);
            return msg;
        } catch (BattleException e) {
            log("ERROR: " + e.getMessage());
            return e.getMessage();
        }
    }

    private void advanceIfMonsterDefeated() {
        if (currentMonster.isDefeated()) {
            log(currentMonster.getName() + " is defeated!");
            round++;
            if (round <= monsters.size()) {
                currentMonster = monsters.get(round - 1);
                log("A wild " + currentMonster.getName() + " appears!");
            }
        }
    }

    public boolean isBattleOver() {
        return player.isDefeated() || (round > monsters.size() && currentMonster.isDefeated());
    }

    public boolean playerWon() {
        return !player.isDefeated() && round > monsters.size() && currentMonster.isDefeated();
    }

    public String getBattleLog() {
        return battleLog.toString();
    }

    public Player getPlayer() { return player; }
    public Monster getCurrentMonster() { return currentMonster; }
    public int getRound() { return round; }

    private void log(String entry) {
        battleLog.append(entry).append("\n");
    }
}
