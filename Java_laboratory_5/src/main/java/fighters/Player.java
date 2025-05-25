/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fighters;

import lab5.Item;

public class Player extends Fighter {
    
    private int points;
    private int experience;
    private int nextLevelExperience;
    private Item[] items;
    private String lastAction = ""; 
    private int lastDamageGiven = 0;

    public Player(int level, int maxhealth, int damage) {
        super(level, maxhealth, damage);
        points = 0;
        experience = 0;
        nextLevelExperience = 40;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getNextLevelExperience() {
        return nextLevelExperience;
    }

    public void setNextLevelExperience(int nextLevelExperience) {
        this.nextLevelExperience = nextLevelExperience;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
    
    
    public String getLastAction() {
        return lastAction;
    }

    public int getLastDamageGiven() {
        return lastDamageGiven;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public void setLastDamageGiven(int lastDamageGiven) {
        this.lastDamageGiven = lastDamageGiven;
    }
    
    
    @Override
    public String getName() {
        return "You";
    }

}
