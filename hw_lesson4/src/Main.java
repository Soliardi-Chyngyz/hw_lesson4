import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.Random;

public class Main {

    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 250, 270, 300, 400, 250, 230};
    public static int[] heroesDamage = {20, 15, 25, 10, 15, 10};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk"};
    public static int healing = 40;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeDefence() {
        Random random = new Random();
        int randomNumber = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomNumber];
        System.out.println("Boss got " + bossDefenceType);
    }

    public static void round() {
        changeDefence();
        heroesHit();
        if (bossHealth > 0) {
            bossHits();
        }
        heal();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void printStatistics() {
        System.out.println("________________");
        System.out.println("Boss's health: " + bossHealth);
        System.out.println("Phisical's health: " + heroesHealth[0]);
        System.out.println("Magical's health: " + heroesHealth[1]);
        System.out.println("Kinetic's health: " + heroesHealth[2]);
        System.out.println("Medic's health: " + heroesHealth[3]);
        System.out.println("Golem's health: " + heroesHealth[4]);
        System.out.println("Lucky's health: " + heroesHealth[5]);
        System.out.println("Berserk's health" + heroesHealth[6]);
        System.out.println("________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    if (heroesHealth[5] > 0 || heroesHealth[4] > 0) {
                        int is = bossDamage / 5;
                        if (i == 4) {
                            heroesHealth[4] = heroesHealth[4] - bossDamage - 50;
                        } else if (i == 5) {
                            Random random = new Random();
                            int r = random.nextInt(1);
                            if (r == 1) {
                                heroesHealth[5] = heroesHealth[5] - bossDamage + bossDamage;
                                System.out.println("Lucky увернулся от удара Босса");
                            }
                        } else {
                            heroesHealth[i] = heroesHealth[i] - bossDamage + is;
                        }
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coef = random.nextInt(8) + 2; // 2,3,4,5,6
                    System.out.println("Critical damage "
                            + heroesDamage[i] * coef);
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
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

    public static void heal() {
        Random random = new Random();
        int num = random.nextInt(heroesDamage.length);
        if (heroesHealth[num] < 100 && heroesHealth[3] > 0 && heroesHealth[num] > 0) {
            heroesHealth[num] += healing;
            System.out.println("Medic heal " + heroesAttackType[num]);
        }
    }

    public static void golim() {

    }
}
