/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_laboratory_5;

/**
 *
 * @author Владислав
 */
import fighters.Enemy;
import fightActions.Debuff;
import fightActions.FightAction;
import fightActions.Hit;
import fightActions.Heal;
import fightActions.Block;
import fighters.Player;
import java.util.ArrayList;

public class Fight {

    Mediator mediator;
    Player player;
    Enemy enemy;
    public Location location = new Location();
    public ArrayList<FightAction> actionsList = new ArrayList<>() {
        {
            add(new Hit());
            add(new Block());
            add(new Debuff());
            add(new Heal());
        }
    };

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Enemy getEnemy() {
        return this.enemy;
    }

    public void playerMove(FightAction enemyAction, FightAction playerAction) {
        mediator.setActionLabels(enemy, player, enemyAction, playerAction);
        playerAction.realisation(player, enemy, enemyAction.getType());
    }

    public void enemyMove(FightAction enemyAction, FightAction playerAction) {
        mediator.setActionLabels(player, enemy, enemyAction, playerAction);
        playerAction.realisation(enemy, player, enemyAction.getType());
    }

    public void checkDebuff() {
        if (!enemy.isDebuffed()) {
            mediator.setDebuffLabel(enemy, false);
        }
        if (enemy.isDebuffed()) {
            mediator.setDebuffLabel(enemy, true);
            enemy.loseDebuffTurn();
        }
        if (!player.isDebuffed()) {
            mediator.setDebuffLabel(player, false);
        }
        if (player.isDebuffed()) {
            mediator.setDebuffLabel(player, true);
            player.loseDebuffTurn();
        }
        
        if (enemy.isWeakened()) {
            enemy.weakenTurnPassed();
            // визуализация метки
            mediator.setWeakenedLabel(enemy, true);
        } else {
            mediator.setWeakenedLabel(enemy, false);
        }

        if (player.isWeakened()) {
            player.weakenTurnPassed();
            mediator.setWeakenedLabel(player, true);
        } else {
            mediator.setWeakenedLabel(player, false);
        }

    }

    public void hit(int a, ArrayList<Result> results, int locationsNumber, Enemy[] enemiesList) {
        GameActions action = new GameActions();
        FightAction enemyAction = action.chooseEnemyAction(enemy, new ArrayList<>(actionsList));


        // Обычная логика боя, если никто не оглушён
        switch (a) {
            case 0 -> { // Атака
                playerMove(enemyAction, actionsList.get(1)); // Hit
                if (enemy.getHealth() > 0) {
                    enemyMove(actionsList.get(1), enemyAction);
                }
                player.setLastAction("Hit");
                player.setLastDamageGiven(player.getDamage());
            }
            case 1 -> { // Защита
                playerMove(enemyAction, actionsList.get(0)); // Block
                if (enemy.getHealth() > 0) {
                    enemyMove(actionsList.get(0), enemyAction);
                }
                player.setLastAction("Block");
                player.setLastDamageGiven(0);
            }
            case 2 -> { // Дебафф
                playerMove(enemyAction, actionsList.get(2)); // Debuff
                if (enemy.getHealth() > 0) {
                    enemyMove(actionsList.get(2), enemyAction);
                }
                player.setLastAction("Debuff");
                player.setLastDamageGiven(0);
            }
        }

        // Обновление информации на экране
        mediator.setRoundTexts(player, enemy);
        checkDebuff();
        mediator.setStunLabel(player);
        mediator.setHealthBar(player);
        mediator.setHealthBar(enemy);

        checkDeath(results, locationsNumber, enemiesList);
    }

    public void checkDeath(ArrayList<Result> results, int locationsNumber, Enemy[] enemiesList) {
        if (player.getHealth() <= 0 & player.getItems()[2].getCount() > 0) {
            player.setHealth((int) (player.getMaxHealth() * 0.05));
            player.getItems()[2].setCount(-1);
            mediator.setHealthBar(player);
            mediator.revive(player, player.getItems());
        }
        if (player.getHealth() <= 0 | enemy.getHealth() <= 0) {
            if (location.getCurrentLocation() == location.getTotalLocations() & "Усач".equals(enemy.getName())) {
                location.resetLocation(false, 1);
                endFinalRound(results, enemiesList);
            } else {
                endRound(enemiesList);
            }
        }
    }

    public void endRound(Enemy[] enemiesList) {
        GameActions action = new GameActions();
        mediator.setEndFightDialog();
        if (player.getHealth() > 0) {
            mediator.setRoundEndText("You win");
            mediator.setGIF(true);
            if ("Усач".equals(enemy.getName())) {
                action.addItems(38, 23, 8, player.getItems());
                action.addPointsBoss(player);
                location.resetLocation(true, player.getLevel());
            } else {
                action.addItems(25, 15, 5, player.getItems());
                action.addPoints(player);
            }
        } else {
            reset(enemiesList);
            mediator.setRoundEndText(enemy.getName() + " win");
            mediator.setGIF(false);

        }
    }

    public void reset(Enemy[] enemiesList) {
        GameActions action = new GameActions();
        player.setDamage(20);
        player.setHealth(10);
        player.setMaxHealth(10);
        action.resetEnemies(enemiesList);
        player.setLevel(0);
        player.setPoints(0);
        player.setExperience(0);
        player.setNextLevelExperience(40);
        location.setFullEnemiesList(enemiesList);
        location.resetLocation(false, player.getLevel());
    }

    public void endFinalRound(ArrayList<Result> results, Enemy[] enemiesList) {
        GameActions action = new GameActions();
        action.resetEnemies(enemiesList);
        String text = "Победа не на вашей стороне";
        if (player.getHealth() > 0) {
            action.addPoints(player);
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int a = 0;
            for (int j = 0; j < results.size(); j++) {
                if (player.getPoints() < results.get(j).getPoints()) {
                    a++;
                }
            }
            if (a < 10) {
                top = true;
            }
        }
        mediator.gameEnding(text, top);
    }

    public void newRound() {
        mediator.setPlayerMaxHealthBar(player);
        mediator.setEnemyMaxHealthBar(enemy);
        player.setHealth(player.getMaxHealth());
        enemy.setHealth(enemy.getMaxHealth());
        mediator.setHealthBar(player);
        mediator.setHealthBar(enemy);
        mediator.setStunLabel(null);
    }

}
