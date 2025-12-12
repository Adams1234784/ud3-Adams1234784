// src/main/java/cs112/ud3/BattleController.java
package cs112.ud3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class BattleController {

    @FXML private Label playerNameLabel;
    @FXML private Label monsterNameLabel;
    @FXML private Label roundLabel;

    @FXML private ProgressBar playerHealthBar;
    @FXML private ProgressBar monsterHealthBar;

    @FXML private Label playerHpLabel;
    @FXML private Label monsterHpLabel;

    @FXML private Button startButton;
    @FXML private Button attackButton;
    @FXML private Button specialButton;
    @FXML private Button healButton;

    @FXML private TextArea battleLogArea;

    private BattleSystem battleSystem;

    @FXML
    private void initialize() {
        attackButton.setDisable(true);
        specialButton.setDisable(true);
        healButton.setDisable(true);
        battleLogArea.setEditable(false);
        battleLogArea.setWrapText(true);
        roundLabel.setText("Round —");
        updateBars(0, 1, 0, 1);
    }

    @FXML
    private void onStart() {
        Player player = new Player("Hero", 100, 18, 6);
        battleSystem = new BattleSystem(player);

        playerNameLabel.setText(player.getName());
        monsterNameLabel.setText(battleSystem.getCurrentMonster().getName());
        roundLabel.setText("Round " + battleSystem.getRound());

        battleLogArea.setText(battleSystem.getBattleLog());
        updateUI();

        startButton.setDisable(true);
        attackButton.setDisable(false);
        specialButton.setDisable(false);
        healButton.setDisable(false);
    }

    @FXML
    private void onAttack() {
        if (battleSystem == null) return;
        appendLog(battleSystem.playerAttack());
        afterPlayerAction();
    }

    @FXML
    private void onSpecial() {
        if (battleSystem == null) return;
        appendLog(battleSystem.playerSpecialAttack());
        afterPlayerAction();
    }

    @FXML
    private void onHeal() {
        if (battleSystem == null) return;
        appendLog(battleSystem.playerHeal());
        // Healing does not trigger immediate monster attack; keep flow consistent:
        if (!battleSystem.isBattleOver()) {
            appendLog(battleSystem.monsterTurn());
        }
        updateUI();
        endBattleIfNeeded();
    }

    private void afterPlayerAction() {
        if (!battleSystem.isBattleOver()) {
            appendLog(battleSystem.monsterTurn());
        }
        updateUI();
        endBattleIfNeeded();
    }

    private void endBattleIfNeeded() {
        if (battleSystem.isBattleOver()) {
            attackButton.setDisable(true);
            specialButton.setDisable(true);
            healButton.setDisable(true);
            String finalMsg = battleSystem.playerWon() ? "You won the Battle Arena!" : "Defeat... Try again.";
            appendLog(finalMsg);
        } else {
            // Update monster name in case a new one spawned
            monsterNameLabel.setText(battleSystem.getCurrentMonster().getName());
            roundLabel.setText("Round " + battleSystem.getRound());
        }
    }

    private void updateUI() {
        Player p = battleSystem.getPlayer();
        Monster m = battleSystem.getCurrentMonster();
        playerHpLabel.setText(p.getHealth() + " / " + p.getMaxHealth());
        monsterHpLabel.setText(m.getHealth() + " / " + m.getMaxHealth());
        updateBars(p.getHealthPercentage(), 1.0, m.getHealthPercentage(), 1.0);
        battleLogArea.setText(battleSystem.getBattleLog());
        battleLogArea.setScrollTop(Double.MAX_VALUE);
    }

    private void updateBars(double pPct, double pMax, double mPct, double mMax) {
        playerHealthBar.setProgress(Math.max(0.0, Math.min(1.0, pPct)));
        monsterHealthBar.setProgress(Math.max(0.0, Math.min(1.0, mPct)));
    }

    private void appendLog(String line) {
        battleLogArea.appendText(line + "\n");
    }
}
