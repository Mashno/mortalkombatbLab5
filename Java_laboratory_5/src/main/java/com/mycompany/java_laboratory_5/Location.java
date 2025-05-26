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
import java.util.ArrayList;

public class Location {

    public int currentLocation = 1;
    public int currentEnemyNumber = 0;
    ArrayList<Enemy> currentEnemiesList = new ArrayList<>();
    Enemy[] fullEnemiesList = null;
    public int locationSize;

    public void setFullEnemiesList(Enemy[] list) {
        fullEnemiesList = list;
    }

    public ArrayList<Enemy> getEnemiesAtLocation() {
        return currentEnemiesList;
    }

    public void setEnemiesAtLocation(int playerLevel, int locationNumber) {
        currentEnemiesList = new ArrayList<>();
        Enemy enemy;

        int minEnemies = 2;
        int maxEnemies = playerLevel + 2;
        locationSize = minEnemies + (int) (Math.random() * (Math.max(1, maxEnemies - minEnemies + 1)));

        for (int j = 0; j < locationSize; j++) {
            int k = (int) (Math.random() * 4);
            int baseHealth, baseDamage;
            EnemyType type;
            String name = "";
            String icon = "";

            switch (k) {
                case 0 -> {
                    name = "Baraka";
                    baseHealth = 100;
                    baseDamage = 12;
                    type = EnemyType.TANK;
                    icon = "B.jpg";
                }
                case 1 -> {
                    name = "Sub-Zero";
                    baseHealth = 60;
                    baseDamage = 16;
                    type = EnemyType.MAGE;
                    icon = "SZ.jpg";
                }
                case 2 -> {
                    name = "Liu Kang";
                    baseHealth = 70;
                    baseDamage = 20;
                    type = EnemyType.FIGHTER;
                    icon = "LK.jpg";
                }
                case 3 -> {
                    name = "Sonya Blade";
                    baseHealth = 80;
                    baseDamage = 16;
                    type = EnemyType.SOLDIER;
                    icon = "SB.jpg";
                }
                default -> {
                    name = "Unknown";
                    baseHealth = 50;
                    baseDamage = 10;
                    type = EnemyType.SOLDIER;
                    icon = "";
                }
            }

            // 📈 Увеличение характеристик на +20% за каждую локацию (начиная со 2-й)
            double scaleMultiplier = 1 + 0.2 * (locationNumber - 1);
            int scaledHealth = (int) (baseHealth * scaleMultiplier);
            int scaledDamage = (int) (baseDamage * scaleMultiplier);

            enemy = new Enemy(name, locationNumber, scaledHealth, scaledDamage, type);
            enemy.setIcon(icon);
            currentEnemiesList.add(enemy);
        }
    }


    public void resetLocation(boolean a, int playerLevel) {
        if (a) {
            currentLocation += 1;
            currentEnemyNumber = 0;
            setEnemiesAtLocation(playerLevel, currentLocation); // передаем и уровень, и локацию
        } else {
            currentLocation = 1;
            currentEnemyNumber = 0;
            setEnemiesAtLocation(playerLevel, currentLocation);
        }
    }


    public int getCurrentLocation() {
        return currentLocation;
    }

    public int getCurrentEnemyNumber() {
        return currentEnemyNumber;
    }

    public Enemy getCurrentEnemy() {
        Enemy enemy = null;
        if (currentEnemyNumber != locationSize) {
            currentEnemyNumber += 1;
            return currentEnemiesList.get(currentEnemyNumber - 1);
        } else {
            enemy = fullEnemiesList[4]; // Boss
            enemy.setIcon("SecretBoss.jpg");

            // 🛡 Усиление характеристик босса в зависимости от локации
            double healthMultiplier = 1 + 0.2 * (currentLocation - 1);
            double damageMultiplier = 1 + 0.15 * (currentLocation - 1);

            int newMaxHealth = (int) (enemy.getMaxHealth() * healthMultiplier);
            int newDamage = (int) (enemy.getDamage() * damageMultiplier);

            enemy.setMaxHealth(newMaxHealth);
            enemy.setHealth(newMaxHealth);
            enemy.setDamage(newDamage);
            enemy.setLevel(currentLocation);  // Можно установить уровень по локации

            return enemy;
        }
    }
}
