package com.pluralsight;
import java.util.Scanner;

public class RentalCar {
    public static void main(String[] args) {
        //Initialize scanner
        Scanner s = new Scanner(System.in);

        //Initialize important price variables
        double baseRate = 29.99;
        double tollTagRate = 3.95;
        double gpsRate = 2.95;
        double roadsideRate = 3.95;
        double underageCharge = 0.3;

        //Get important variables from user
        String pickupDate = getPickupDate(s);
        int rentalDays = getRentalDays(s);
        int userAge = getUserAge(s);

        //Flag rental addon choices from user
        s.nextLine();
        boolean toll = addTollTag(tollTagRate, s);
        boolean gps = addGPS(gpsRate, s);
        boolean roadside = addRoadside(roadsideRate, s);

        //Print final prices
        double baseCost = calcBasicCost(baseRate, rentalDays);
        System.out.println("The initial price of your rental is $" + baseCost + ".");

        double optionsCost = calcOptionsCost(tollTagRate, toll, gpsRate, gps, roadsideRate, roadside, rentalDays);
        if (toll || gps || roadside)
            System.out.println("Based on the options you selected, there is an additional cost of $" + optionsCost + ".");

        double surcharge = calcSurcharge(baseCost, userAge, underageCharge);
        if (userAge < 25)
            System.out.println("Based on your age, there is an additional surcharge of $" + surcharge + ".");

        double totalCost = calcTotalCost(baseCost, optionsCost, surcharge);
        System.out.println("The total cost for your rental is $" + totalCost + ".");

    }

    public static String getPickupDate(Scanner s) {
        System.out.println("Input the date that you will be picking up the rental vehicle.");
        return s.nextLine();
    }

    public static int getRentalDays(Scanner s) {
        System.out.println("Input how many days you plan to rent this vehicle.");
        return s.nextInt();
    }

    public static int getUserAge(Scanner s) {
        System.out.println("How old are you currently?");
        return s.nextInt();
    }

    public static boolean addTollTag(double tollTagRate, Scanner s) {
        System.out.println("Do you want a toll tag on your vehicle? This comes with an additional charge of $" + tollTagRate + " per day.");
        return s.nextLine().equalsIgnoreCase("yes");
    }

    public static boolean addGPS(double gpsRate, Scanner s) {
        System.out.println("Do you want a GPS with your vehicle? This comes with an additional charge of $" + gpsRate + " per day.");
        return s.nextLine().equalsIgnoreCase("yes");
    }

    public static boolean addRoadside(double roadsideRate, Scanner s) {
        System.out.println("Do you want roadside assistance availible? This comes with an additional charge of $" + roadsideRate + " per day.");
        return s.nextLine().equalsIgnoreCase("yes");
    }

    public static double calcBasicCost(double baseRate, int rentalDays) {
        return baseRate * rentalDays;
    }

    public static double calcOptionsCost(double tollTagRate, boolean toll, double gpsRate, boolean gps, double roadsideRate, boolean roadside, int rentalDays) {
        return ((toll ? tollTagRate : 0) + (gps ? gpsRate : 0) + (roadside ? roadsideRate : 0)) * rentalDays;
    }

    public static double calcSurcharge(double baseCost, int userAge, double underageCharge) {
        return baseCost * (userAge < 25 ? underageCharge : 0);
    }

    public static double calcTotalCost(double baseCost, double optionsCost, double surcharge) {
        return baseCost + optionsCost + surcharge;
    }
}
