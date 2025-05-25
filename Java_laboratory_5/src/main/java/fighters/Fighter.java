/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fighters;

import javax.swing.ImageIcon;

public abstract class Fighter {
    
    private int level;
    private int health;
    private int maxHealth;
    private int damage;
    private ImageIcon icon;
    private int movesWithDebuff;
    private boolean isStunned = false;//добавление оглушения
    private boolean isWeakened = false;
    private int weakenDuration = 0;
    
    public Fighter(int level, int maxhealth, int damage) {
        this.level = level;
        this.health = maxhealth;
        this.maxHealth = maxhealth;
        this.damage = damage;
        movesWithDebuff = 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(String path) {
        icon = new ImageIcon(path);
    }

    public int getMovesWithDebuff() {
        return movesWithDebuff;
    }

    public void setMovesWithDebuff(int movesWithDebuff) {
        this.movesWithDebuff = movesWithDebuff;
    }
    
    public boolean isDebuffed() {
        return movesWithDebuff > 0;
    }
    
    public void loseDebuffTurn() {
        if (movesWithDebuff > 0) {
            movesWithDebuff--;
        }
    }
    
    public boolean isStunned() {
        return isStunned;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }
    
    public abstract String getName();
    
    public boolean isWeakened() { return isWeakened; }
    public void setWeakened(boolean weakened, int duration) {
        this.isWeakened = weakened;
        this.weakenDuration = duration;
    }
    public void weakenTurnPassed() {
        if (weakenDuration > 0) {
            weakenDuration--;
            if (weakenDuration == 0) isWeakened = false;
        }
    }

    public void setIsStunned(boolean isStunned) {
        this.isStunned = isStunned;
    }

    public void setWeakened(boolean Weakened) {
        this.isWeakened = Weakened;
    }
    
    
}
