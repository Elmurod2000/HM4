package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";

    public static int[] heroesHealth = {270, 260, 250, 250,350,400,410,440};
    public static int[] heroesDamage = {15, 20, 25, 0,5,7,10,15};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic","Golem","Lucky","Berserk","Thor"};

    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics(); // До начала игры вывод статистики
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); //0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round_number++;
        changeBossDefence();
        bossHits();
        heroesHit();
        medic();
        golem();
        Lucky();
        Berserk();
        Thor();
        printStatistics();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 3) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                heroesHealth[i] += 50;
                System.out.println("Медик излечил" + heroesAttackType[i]);
                break;
            }

        }
    }
public static void golem (){
        int uronFromBoss=bossDamage/5;
        int liveHeroes=0;
        if (heroesHealth[4]>0){
            for (int i = 0; i < heroesDamage.length; i++) {
                if (i==4){
                    continue;
                } else if (heroesHealth[i]>0){
                    liveHeroes++;
                    heroesHealth[i]+=uronFromBoss;
                }
            }
            heroesHealth[4]-=uronFromBoss*liveHeroes;
            System.out.println("Голем получил урон "+uronFromBoss*liveHeroes);
        }

}
public static void Lucky(){
        Random random=new Random();
        boolean Luckly=random.nextBoolean();
        if (heroesHealth[5] >0){
            if (Luckly) heroesHealth[5]+=bossDamage-10;
            System.out.println("Lucky не получил удар"+Luckly);

        }

}
public static void Berserk(){
    for (int i = 0; i < heroesHealth.length; i++) {
        if (heroesHealth[6] > 0) {
            heroesHealth[6]-=bossDamage*1/10;
            heroesDamage[6]+=bossDamage*1/10;
            System.out.println("Berserk заблокировал урон");
            break;
        }

    }
}
public static void Thor(){
        Random random=new Random();
        boolean oglushka= random.nextBoolean();
    for (int i = 0; i < heroesHealth.length; i++) {
        if (heroesHealth[7]>0){
            if (oglushka){
                bossDamage=0;
                System.out.println("Thor zaglushil"+oglushka);
           break;

            }
            else bossDamage=50;
            break;
        }

    }
}

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND _______________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
        System.out.println("_______________");
    }
}

