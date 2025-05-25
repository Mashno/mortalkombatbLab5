/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fighters;

import lab5.EnemyType;

public class Enemy extends Fighter{

    private String name;
    private EnemyType type;

    public Enemy(String name, int level, int maxhealth, int damage,EnemyType type ) {
        super(level, maxhealth, damage);
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public EnemyType getType() {
        return type;
    }
    
    public void setType(EnemyType type) {
        this.type = type;
    }
}
