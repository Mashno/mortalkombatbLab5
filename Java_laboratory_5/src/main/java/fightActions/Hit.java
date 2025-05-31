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

public class Hit extends FightAction {

    @Override
    public String getType() {
        return "Hit";
    }

    @Override
    public void realisation(Fighter fighter1, Fighter fighter2, String fighter2ActionType) {
        int baseDamage = fighter1.getDamage();

        //уменьшение урона, если нападающий ослаблен
        if (fighter1.isWeakened()) {
            baseDamage *= 0.5;
        }

        //усиление урона, если цель ослаблена
        if (fighter2.isWeakened()) {
            baseDamage *= 1.25;
        }

        switch (fighter2ActionType) {
            case "Hit" -> {
                //только первый наносит урон
                fighter2.setHealth(fighter2.getHealth() - baseDamage);
            }

            case "Block" -> {
                //контрудар защитника: 50% от его урона
                int counterDamage = (int) (fighter2.getDamage() * 0.5);
                fighter1.setHealth(fighter1.getHealth() - counterDamage);
            }

            case "Debuff" -> {
                //дебафф срывается, атакующий наносит +15% урона
                int bonusDamage = (int) (baseDamage * 1.15);
                fighter2.setHealth(fighter2.getHealth() - bonusDamage);
            }

            case "Heal" -> {
                //удвоенный урон по цели
                fighter2.setHealth(fighter2.getHealth() - baseDamage );
            }
        }
    }

}
