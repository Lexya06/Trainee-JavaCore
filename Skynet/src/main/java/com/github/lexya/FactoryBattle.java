package com.github.lexya;
import static com.github.lexya.Skynet.*;
import java.util.concurrent.Phaser;

public class FactoryBattle {
    static final int DAYS = 100;
    static String[] factionNames = {"Wednesday","World"};
    static Faction[] factions = new Faction[factionNames.length];
    static Thread[] threadFactions = new Thread[factions.length];
    static final Phaser phaser = new Phaser(factions.length + 1);
    public static void main(String[] args) {
        for (int j = 0; j < factions.length; j++) {
            factions[j] = new Faction(factionNames[j]);
        }

        // starting threads for factions and waiting for factory generation details
        for (int i = 0; i < factions.length; i++) {
            Faction f = factions[i];
            threadFactions[i] = new Thread(() -> {
                for (int day = 0; day < DAYS; day++) {
                    phaser.arriveAndAwaitAdvance();
                    f.carryDetailsAndGetRobots();
                }
            });
            threadFactions[i].start();
        }

        for (int day = 0; day < DAYS; day++) {
            Factory.GenerateDetailsToDivide();
            // move to next phase
            phaser.arriveAndAwaitAdvance();
        }

        for (Thread t : threadFactions) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


        int maxRobots = 0;
        int factionWithMaxArmyNumber = 0;
        for (int i = 0; i < factions.length; i++) {
            if (factions[i].robots > maxRobots) {
                maxRobots = factions[i].robots;
                factionWithMaxArmyNumber = i;
            }
            System.out.println("Faction " + factions[i].getName() + " has "+factions[i].robots+" robots");
        }
        System.out.println("Faction with max army number is "+factions[factionWithMaxArmyNumber].getName()+
                ",army: "+maxRobots + " robots");


    }
}
