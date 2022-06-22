package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;

    public static int[] heroesHealth = {270, 280, 260, 350};
    public static int[] heroesDamage = {20, 15, 25, 0};

    public static String[] heroesAttackType = {"Physical", "Magic", "Kinetic", "Medic"};
    public static String bossDefenseType = "";

    public static int roundNumber = 0;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished())
            round();
    }

    public static Boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes Won!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!");
            return true;
        }
        return false;
    }

    public static void printStatistics() {
        System.out.println("********" + roundNumber + "Round********");
        System.out.println("Boss Health; " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesDamage.length; i++) {
            System.out.println(heroesAttackType[i] + " health " + heroesHealth[i] + " [" + heroesDamage[i] + "]");

        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < bossDamage) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
            if (heroesHealth[0] < 100 && heroesHealth[0]!=0 && heroesHealth[3] !=0){
                heroesHealth[0] = +100;}
            if (heroesHealth[1] < 100 && heroesHealth[1]!=0 && heroesHealth[3] !=0){
                heroesHealth[1] = +100;}
            if (heroesHealth[2] < 100 && heroesHealth[2]!=0 && heroesHealth[3] !=0){
                heroesHealth[2] = +100;}

        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {

            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenseType == heroesAttackType[i]) {
                    Random r = new Random();
                    int coef = r.nextInt(5) + 2;
                    if (bossHealth < heroesDamage[i] * coef) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println("Critical Damage: " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth < heroesDamage[i]) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void chooseBossDefenceType() { //случайный тип защиты
        Random random = new Random();
        int randomIndex = random.nextInt(3);
        bossDefenseType = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefenseType);
    }

    public static void round() {
        roundNumber++;
        chooseBossDefenceType();
        bossHits();
        heroesHit();
        printStatistics();

    }
}