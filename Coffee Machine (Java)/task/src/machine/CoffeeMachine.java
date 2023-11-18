package machine;

import java.util.Scanner;

public class CoffeeMachine {
    // Stores the number of ml of water available
    static int mlOfWater = 400;
    //Stores the number of ml of milk available
    static int mlOfMilk = 540;
    // Stores the number of grams of coffee beans available
    static int gOfCoffeeBeans = 120;
    // Stores the number of disposable cups available
    static int numOfDisposableCups = 9;
    // Stores the amount of money currently in the machine
    static int numOfEarnedMoney = 550;
    // Global scanner
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("machine.Espresso");
        Class.forName("machine.Latte");
        Class.forName("machine.Cappuccino");
        while (true) {
            if (makeChoice()) {
                break;
            }
        }
    }

    /**
     * Takes user input and executes the corresponding command. Return type is boolean so that it can return true to exit the loop.
     */
    public static boolean makeChoice() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String choice = scanner.next();
        if ("buy".equals(choice)) {
            buyCoffee();
        } else if ("fill".equals(choice)) {
            fillMachine();
        } else if ("take".equals(choice)) {
            takeMoney();
        } else if ("remaining".equals(choice)) {
            printSuppliesStatus();
        } else return "exit".equals(choice);
        return false;
    }

    /**
     * Buys a singular cup of coffee. Takes user input and buys the corresponding coffee and changes the amounts of the supplies available accordingly
     */
    public static void buyCoffee() {
        // Need to fix this, the system for checking if there is enough supplies is not working
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String choice = scanner.next();
        if ("1".equals(choice)) {
            if (Espresso.isEnoughSupplies()) {
                System.out.println("I have enough resources, making you a coffee!");
                mlOfWater -= 250;
                gOfCoffeeBeans -= 16;
                numOfDisposableCups -= 1;
                numOfEarnedMoney += 4;
            } else {
                Espresso.printInsufficientSupplies();
            }
        } else if ("2".equals(choice)) {
            if (Latte.isEnoughSupplies()) {
                System.out.println("I have enough resources, making you a coffee!");
                mlOfWater -= 350;
                mlOfMilk -= 75;
                gOfCoffeeBeans -= 20;
                numOfDisposableCups -= 1;
                numOfEarnedMoney += 7;
            } else {
                Latte.printInsufficientSupplies();
            }
        } else if ("3".equals(choice)) {
            if (Cappuccino.isEnoughSupplies()) {
                System.out.println("I have enough resources, making you a coffee!");
                mlOfWater -= 200;
                mlOfMilk -= 100;
                gOfCoffeeBeans -= 12;
                numOfDisposableCups -= 1;
                numOfEarnedMoney += 6;
            } else {
                Cappuccino.printInsufficientSupplies();
            }
        } else if ("back".equals(choice)) {
            System.out.println();
            return;
        }
        System.out.println();
    }

    /**
     * Fills each supply in the machine with what the user specifies
     */
    public static void fillMachine() {
        System.out.println("Write how many ml of water you want to add:");
        mlOfWater += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        mlOfMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        gOfCoffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        numOfDisposableCups += scanner.nextInt();
        System.out.println();
    }

    /**
     * Gives the user all the money in the machine, setting the money variable to 0
     */
    public static void takeMoney() {
        System.out.printf("I gave you $%d\n", numOfEarnedMoney);
        numOfEarnedMoney = 0;
        System.out.println();
    }

    /**
     * Prints the amount of each supply that is currently available, excluding the money
     */
    public static void printSuppliesStatus() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", mlOfWater);
        System.out.printf("%d ml of milk\n", mlOfMilk);
        System.out.printf("%d g of coffee beans\n", gOfCoffeeBeans);
        System.out.printf("%d disposable cups\n", numOfDisposableCups);
        System.out.printf("$%d of money\n", numOfEarnedMoney);
        System.out.println();
    }





    /*
        All of these functions below are vestigial functions
     */





    /**
     * Prints out how much of each supply the machine will need in order to make as many coffee cups as the user specifies
     */
    public static void printNeededIngredientsAmounts() {
        System.out.print("How many coffee cups will you need? ");
        int amountOfCoffeeCups = scanner.nextInt();
        int mlOfWater = amountOfCoffeeCups * 200;
        int mlOfMilk = amountOfCoffeeCups * 50;
        int gOfCoffeeBeans = amountOfCoffeeCups * 15;
        System.out.println("Here's all of the ingredients you will need for " + amountOfCoffeeCups + " coffee cups:");
        System.out.println(mlOfWater + " ml of water");
        System.out.println(mlOfMilk + " ml of milk");
        System.out.println(gOfCoffeeBeans + " g of coffee beans");
    }

    /**
     * Takes user input for how much of each supply is in the machine, takes user input for how many coffee cups they
     * want, and prints if, with the supplies that the user input, it can make exactly that many coffee cups, that many coffee
     * cups and some more, or less than that many coffee cups
     */
    public static void checkIfCanMakeCoffeeCupAmount() {
        System.out.println("Write how many ml of water the coffee machine has:");
        int mlOfWater = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int mlOfMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int gOfCoffeeBeans = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        int coffeeCupAmount = scanner.nextInt();
        int maxCoffeeCupAmount = coffeeCupAmountFromIngredients(mlOfWater, mlOfMilk, gOfCoffeeBeans);
        int coffeeCupDifference = maxCoffeeCupAmount - coffeeCupAmount;

        if (coffeeCupDifference > 0) {
            System.out.println("Yes, I can make that amount of coffee (and even " + coffeeCupDifference + " more than that)");
        } else if (coffeeCupDifference == 0) {
            System.out.println("Yes, I can make that amount of coffee");
        } else {
            System.out.println("No, I can make only " + maxCoffeeCupAmount + " cup(s) of coffee");
        }
    }

    /**
     * Function used by the checkIfCanMakeCoffeeCupAmount() function which returns how many coffee cups it can make from the
     * amount of each supply that is passed into the function
     */
    public static int coffeeCupAmountFromIngredients(int mlOfWater, int mlOfMilk, int gOfCoffeeBeans) {
        int waterPortions = mlOfWater / 200;
        int milkPortions = mlOfMilk / 50;
        int coffeeBeansPortions = gOfCoffeeBeans / 15;
        return Math.min(Math.min(waterPortions, milkPortions), coffeeBeansPortions);
    }



}
