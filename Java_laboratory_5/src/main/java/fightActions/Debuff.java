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

public class Debuff extends FightAction {

    @Override
    public String getType() {
        return "Debuff";
    }

    @Override
    public void realisation(Fighter fighter1, Fighter fighter2, String fighter2ActionType) {
        switch (fighter2ActionType) {
            case "Hit" -> {
                // Дебафф срывается, атакующий наносит +15% урона
                int bonusDamage = (int) (fighter2.getDamage() * 1.15);
                fighter1.setHealth(fighter1.getHealth() - bonusDamage);
            }

            case "Block" -> {
                // 75% шанс наложить дебафф на n ходов (n = уровень fighter1)
                if (Math.random() < 0.75) {
                    int turns = Math.max(1, fighter1.getLevel());
                    fighter2.setMovesWithDebuff(turns);
                    fighter2.setWeakened(true);
                }
            }

            case "Debuff" -> {
                // Оба дебаффят — 50% шанс наложения на второго
                if (Math.random() < 0.5) {
                    int turns = Math.max(1, fighter1.getLevel());
                    fighter2.setMovesWithDebuff(turns);
                    fighter2.setWeakened(true);
                }
            }

            case "Heal" -> {
                // Ничего не происходит
            }
        }
    }

}
