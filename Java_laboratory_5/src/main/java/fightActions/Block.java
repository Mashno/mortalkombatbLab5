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


public class Block extends FightAction {

    @Override
    public String getType() {
        return "Block";
    }

    @Override
    public void realisation(Fighter fighter1, Fighter fighter2, String fighter2ActionType) {
        switch (fighter2ActionType) {
            case "Hit" -> {
                
            }

            case "Block" -> {
                
            }

            case "Debuff" -> {
                //при блоке против дебафа - шанс получить дебаф
                if (Math.random() < 0.75) {
                    int turns = Math.max(1, fighter2.getLevel());
                    fighter1.setMovesWithDebuff(turns); // дебаф применяется к первому бойцу (игроку)
                    fighter1.setWeakened(true);
                }
            }

            case "Heal" -> {
                //ничего не происходит
            }
        }
    }

}
