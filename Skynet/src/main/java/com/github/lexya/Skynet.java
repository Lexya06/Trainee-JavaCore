package com.github.lexya;

import java.util.concurrent.locks.ReentrantLock;

public class Skynet {
    static final ReentrantLock detailsLock = new ReentrantLock(true);

    public enum Details {
        HEAD,
        TORSO,
        HANDS,
        FEET
    }

    static int[] detailsToDivide = new int[Details.values().length];

    public static class Factory {
        static int maxDetails = 10;

        public static void GenerateDetailsToDivide() {
            detailsLock.lock();
            getRandomDetails(detailsToDivide, maxDetails + 1, maxDetails, 10);
            detailsLock.unlock();
        }


    }

    private static void getRandomDetails(int[] details, int maxDetails, int getFromValue, int iterations) {
        int detailsCount = 0;
        while (iterations > 0 && detailsCount < maxDetails) {
            int detailType = (int) (Math.random() * Details.values().length);
            int added = (int) ((Math.random() * getFromValue) % (maxDetails - detailsCount));
            details[detailType] += added;
            detailsCount += added;
            iterations--;
        }
    }


    public static class Faction {
        static int maxDetailsCarry = 5;
        int[] robotsDetails;
        int robots;

        private final String name;

        public Faction(String name) {
            this.name = name;
            robots = 0;
            robotsDetails = new int[Details.values().length];
        }

        public String getName() {
            return name;
        }

        private static void getRandomRobotsDetails(int[] details, int[] divideDetails) {
            int detailsCount = 0;
            for (int i = 0; i < divideDetails.length; i++) {
                int temp = details[i];
                getRandomDetails(details, maxDetailsCarry + 1 - detailsCount, divideDetails[i], 1);
                int diff = details[i] - temp;
                detailsCount += diff;
                divideDetails[i] -= diff;
            }
        }

        private static Details getNeededPartsType(int indDetail1, int indDetail2, int[] arr) {
            int minInd;

            int amountOne = arr[indDetail1];
            int amountTwo = arr[indDetail2];
            if (amountOne < amountTwo) {
                minInd = indDetail1;
            } else {
                minInd = indDetail2;
            }
            return Details.values()[minInd];
        }

        private void updateDetails(int singlePartsAmount, int doublePartsAmount) {
            robotsDetails[Details.HEAD.ordinal()] -= singlePartsAmount;
            robotsDetails[Details.TORSO.ordinal()] -= singlePartsAmount;
            robotsDetails[Details.FEET.ordinal()] -= doublePartsAmount;
            robotsDetails[Details.HANDS.ordinal()] -= doublePartsAmount;
        }


        public void carryDetailsAndGetRobots() {
            detailsLock.lock();
            getRandomRobotsDetails(robotsDetails, detailsToDivide);
            detailsLock.unlock();


            Details singlePart = getNeededPartsType(Details.HANDS.ordinal(), Details.TORSO.ordinal(), robotsDetails);
            Details doublePart = getNeededPartsType(Details.HANDS.ordinal(), Details.FEET.ordinal(), robotsDetails);

            int singlePartAmount = robotsDetails[singlePart.ordinal()];
            int doublePartAmount = robotsDetails[doublePart.ordinal()];

            if (doublePartAmount == 0 || singlePartAmount == 0) {
                return;
            }
            int pairsParts = doublePartAmount / 2;
            robots += Math.min(pairsParts, singlePartAmount);

            updateDetails(singlePartAmount, pairsParts * 2);
        }

    }
}

