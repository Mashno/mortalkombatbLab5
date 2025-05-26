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

public abstract class FightAction {
    public abstract String getType();
    public abstract void realisation(Fighter fighter1, Fighter fighter2, String fighter2ActionType);
}
