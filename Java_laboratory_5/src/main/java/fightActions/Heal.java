/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fightActions;

/**
 *
 * @author Владислав
 */


import fighters.Fighter;

public class Heal extends FightAction {

    @Override
    public String getType() {
        return "Heal";
    }

    @Override
    public void realisation(Fighter fighter1, Fighter fighter2, String fighter2ActionType) {
        switch (fighter2ActionType) {
            case "Hit" -> {
                if ("Усач".equals(fighter1.getName())) {
                    // Босс получает двойной урон при атаке игрока во время регенерации
                    int doubleDamage = fighter2.getDamage() * 2;
                    fighter1.setHealth(fighter1.getHealth() - doubleDamage);
                } else {
                    // Игрок лечится, но получает урон
                    fighter1.setHealth((int) (fighter1.getMaxHealth() * 0.25) + fighter1.getHealth());
                    fighter1.setHealth(fighter1.getHealth() - fighter2.getDamage());
                }
            }

            case "Block" -> {
                if ("Усач".equals(fighter1.getName())) {
                    // Босс восстанавливает 50% от недостающего здоровья
                    int missingHealth = fighter1.getMaxHealth() - fighter1.getHealth();
                    fighter1.setHealth(fighter1.getHealth() + missingHealth / 2);
                } else {
                    fighter1.setHealth((fighter1.getMaxHealth() - fighter1.getHealth()) / 2 + fighter1.getHealth());
                }
            }

            case "Debuff" -> {
                // Лечение блокирует дебафф
            }

            case "Heal" -> {
                // Оба лечатся — усиленный эффект
                fighter1.setHealth((fighter1.getMaxHealth() - fighter1.getHealth()) + fighter1.getHealth());
            }
        }
    }
}
