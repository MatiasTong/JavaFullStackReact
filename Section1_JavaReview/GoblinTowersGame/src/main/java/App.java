

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean playAgain = false;
        do {
            Scanner sc = new Scanner(System.in);
            Hero hero = new Hero();
            final double hp = hero.getHP();

            System.out.println("Tread carefully, O mighty warrior!");
            System.out.println("Your Stats:\n" + hero.toString());

            do {
                System.out.println("Press enter to continue");
                sc.nextLine();
                hero.walk();

                //Increase level for every ten steps
                if (hero.getSteps() >= 10 && hero.getSteps() % 10 == 0) {
                    hero.setLevel(hero.getLevel() + 1);
                    System.out.println("You've increased to level " + hero.getLevel());
                    //ask if they want to go to the potion shop
                    if (hero.getPotions().size() < 5) {
                        System.out.println("Would you like to visit the potion shop and buy a potion?(Y/N)");
                        String buyPotion = sc.nextLine().trim().toLowerCase();
                        if (buyPotion.equals("y") || buyPotion.equals("yes")) {
                            hero.buyPotion();
                            System.out.println("Success! You have " + hero.getPotions().size() + " potions now.");
                            System.out.println("Press enter to continue");
                            sc.nextLine();
                        }
                    }
                }
                //Show the current step
                System.out.println("Step: " + hero.getSteps());

                //Set a 33% chance the hero will meet a goblin
                Random rGen = new Random();
                if (rGen.nextInt(3) == 0) {
                    Goblin goblin = new Goblin();
                    //encounters a goblin and they fight
                    System.out.println("A goblin with HP " + goblin.getHP());
                    while (goblin.isAlive() && hero.isAlive()) {
                        System.out.println("you fight!");
                        hero.attack(goblin);
                        System.out.println("goblin attacks!");
                        goblin.attack(hero);
                        System.out.println("Your Stats:\n" + hero.toStringAbbrev());
                        System.out.println("Goblin Stats:\n" + goblin.toString());
                        System.out.println("Press enter to continue");
                        sc.nextLine();
                    }

                    if (hero.isAlive()) {
                        System.out.println("The goblin has been vanquished! You earned two gold dubloons...");
                        hero.earnGold(2);
                        System.out.println("Your Stats:\n" + hero.toString());
                        if (hero.getPotions().size() > 0) {
                            System.out.println("You have " + hero.getPotions().size() + " potions left. Would you like to use your potion? (Y/N)");
                            String usePotion = sc.nextLine().trim().toLowerCase();
                            if (usePotion.equals("y") || usePotion.equals("yes")) {
                                hero.usePotion();
                                System.out.println("Your HP is back to " + hero.getHP() + "!");
                            }
                        }
                    } else {
                        System.out.println("you died game over");
                        System.out.println("Would you like to play again (Y/N)");
                        String answer = sc.nextLine().trim().toLowerCase();
                        if (answer.equals("y") || answer.equals("yes")) {
                            playAgain=true;
                        }
                    }
                }


            } while (hero.isAlive());
        } while(playAgain);
    }
}
