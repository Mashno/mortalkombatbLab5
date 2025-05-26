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
import fightActions.FightAction;
import fighters.Player;
import java.util.ArrayList;

public class GameActions {

    private final int experience_for_next_level[] = {40, 90, 180, 260, 410, 1000};

    public FightAction chooseEnemyAction(Enemy enemy, ArrayList<FightAction> list) {
        double choice = Math.random();

        switch (enemy.getType()) {
            case TANK -> {
                if (choice < 0.6) return list.get(0); // Block
                
                else return list.get(1); // Hit
            }
            case MAGE -> {
                if (choice < 0.5) return list.get(0); // Hit
                else return list.get(2); // Debuff
            }
            case FIGHTER -> {
                if (choice < 0.65) return list.get(1); // Hit
                
                else return list.get(0); // Block
            }
            case SOLDIER -> {
                if (choice < 0.5) return list.get(0); // Block
                else return list.get(1); // Hit
            }
            case BOSS -> {
                // Босс: 40% Hit, 20% Block, 20% Debuff, 20% Heal
                if (choice < 0.4) return list.get(1); // Hit
                else if (choice < 0.6) return list.get(0); // Block
                else return list.get(3); // Heal
            }
            default -> {
                return list.get((int) (Math.random() * list.size()));
            }
        }
    }

    public void addPoints(Player player) {
        player.setExperience(player.getExperience() + 20 + 5 * player.getLevel());
        player.setPoints(20 + 5 * player.getLevel() + player.getHealth() / 4);
    }


    public boolean checkExperience(Player player) {
        return player.getExperience() >= player.getNextLevelExperience();
    }

    public void levelUp(Player player, Enemy[] enemies) {
        player.setLevel(player.getLevel() + 1);

        // Вычитаем опыт, нужный для предыдущего уровня
        int usedExperience = player.getNextLevelExperience();
        player.setExperience(player.getExperience() - usedExperience);

        // Обновляем следующий порог
        int i = 0;
        while (usedExperience >= experience_for_next_level[i]) {
            i++;
        }
        player.setNextLevelExperience(experience_for_next_level[i]);

        // Улучшаем врагов
        for (int j = 0; j < 5; j++) {
            newHealthEnemy(enemies[j], player);
        }
    }


    public void addPointsBoss(Player player) {
        player.setExperience(player.getExperience() + 50);
        player.setPoints(65 + player.getHealth() / 2);
    }

    public void addItems(int k1, int k2, int k3, Item[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }

    public void addHealthPlayer(Player player) {
        player.setMaxHealth(20+5*player.getLevel() + player.getMaxHealth());
    }

    public void addDamagePlayer(Player player) {
        player.setDamage(2+player.getLevel() + player.getDamage());
    }

    public void newHealthEnemy(Enemy enemy, Player player) {//улучшение врагов с уровнем
        switch (enemy.getType()) {
            case TANK -> {
                enemy.setMaxHealth((int)(enemy.getMaxHealth() * 1.1) + enemy.getMaxHealth());
                enemy.setDamage(enemy.getDamage() + 1);
            }
            case MAGE -> {
                enemy.setMaxHealth(enemy.getMaxHealth() + 5);
                enemy.setDamage(enemy.getDamage() + 2);
            }
            case FIGHTER -> {
                enemy.setMaxHealth(enemy.getMaxHealth() + 7);
                enemy.setDamage(enemy.getDamage() + 3);
            }
            case SOLDIER -> {
                enemy.setMaxHealth(enemy.getMaxHealth() + 5);
                enemy.setDamage(enemy.getDamage() + 2);
            }
            default -> {
                enemy.setMaxHealth(enemy.getMaxHealth() + 5);
                enemy.setDamage(enemy.getDamage() + 1);
            }
        }

        enemy.setLevel(enemy.getLevel() + 1);
    }

    public void useItem(Player player, Item[] items, String name, Mediator mediator) {
        boolean a = false;
        switch (name) {
            case "First item" -> {
                if (items[0].getCount() > 0) {
                    player.setHealth((int) (player.getMaxHealth() * 0.25) + player.getHealth());
                    items[0].setCount(-1);
                } else {
                    a = true;
                    mediator.openCantUseItemDialog();
                }
            }
            case "Second item" -> {
                if (items[1].getCount() > 0) {
                    player.setHealth((int) (player.getMaxHealth() * 0.5) + player.getHealth());
                    items[1].setCount(-1);
                } else {
                    a = true;
                    mediator.openCantUseItemDialog();
                }
            }
            case "Third item" -> {
                a = true;
                mediator.openCantUseItemDialog();
            }
        }
    }

    public void resetEnemies(Enemy[] enemiesList) {
        for (Enemy enemy : enemiesList) {
            enemy.setLevel(1);
            switch (enemy.getName()) {
                case "Sub-Zero" -> {
                    enemy.setLevel(1);
                    enemy.setDamage(16);
                    enemy.setMaxHealth(60);
                }
                case "Sonya Blade" -> {
                    enemy.setLevel(1);
                    enemy.setDamage(16);
                    enemy.setMaxHealth(80);
                }
                case "Lord Islam Maximov" -> {
                    enemy.setLevel(1);
                    enemy.setDamage(30);
                    enemy.setMaxHealth(100);
                }
                case "Liu Kang" -> {
                    enemy.setLevel(1);
                    enemy.setDamage(20);
                    enemy.setMaxHealth(70);
                }
                case "Baraka" -> {
                    enemy.setLevel(1);
                    enemy.setDamage(12);
                    enemy.setMaxHealth(100);
                }
            }
        }
    }
}
