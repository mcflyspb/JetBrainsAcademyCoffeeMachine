package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static final String ASK_ACTION = "Write action (buy, fill, take, remaining, exit): ";
    private static final String COFFEE_CHOICE = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:";
    private static final String ADD_WATER = "Write how many ml of water you want to add:";
    private static final String ADD_MILK = "Write how many ml of milk you want to add:";
    private static final String ADD_COFFEE = "Write how many grams of coffee beans you want to add:";
    private static final String ADD_CUPS = "Write how many disposable cups you want to add:";
    private static final int ESPRESSO_WATER_PER_CUP = 250;
    private static final int ESPRESSO_MILK_PER_CUP = 0;
    private static final int ESPRESSO_COFFEE_PER_CUP = 16;
    private static final int ESPRESSO_PRICE = 4;

    private static final int LATTE_WATER_PER_CUP = 350;
    private static final int LATTE_MILK_PER_CUP = 75;
    private static final int LATTE_COFFEE_PER_CUP = 20;
    private static final int LATTE_PRICE = 7;

    private static final int CAPPUCCINO_WATER_PER_CUP = 200;
    private static final int CAPPUCCINO_MILK_PER_CUP = 100;
    private static final int CAPPUCCINO_COFFEE_PER_CUP = 12;
    private static final int CAPPUCCINO_PRICE = 6;

    private static int waterAviable = 400;
    private static int milkAviable = 540;
    private static int coffeeAviable = 120;
    private static int moneyAviable = 550;
    private static int cupsAviable = 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println(ASK_ACTION);
            String action = scanner.nextLine();
            switch (action) {
                case ("buy") -> getBuy(scanner);
                case ("fill") -> getFill(scanner);
                case ("take") -> getTake();
                case ("remaining") -> getAviableCalculations(waterAviable, milkAviable, coffeeAviable, cupsAviable, moneyAviable);
                default -> { return;
                }
            }
        }
    }

    private static void getFill(Scanner scanner) {
        System.out.println(ADD_WATER);
        waterAviable = waterAviable + Integer.parseInt(scanner.nextLine());
        System.out.println(ADD_MILK);
        milkAviable = milkAviable + Integer.parseInt(scanner.nextLine());
        System.out.println(ADD_COFFEE);
        coffeeAviable = coffeeAviable + Integer.parseInt(scanner.nextLine());
        System.out.println(ADD_CUPS);
        cupsAviable = cupsAviable + Integer.parseInt(scanner.nextLine());
    }

    private static void getBuy(Scanner scanner) {
        System.out.println(COFFEE_CHOICE);
        String coffeeChoice = scanner.nextLine();
        switch (coffeeChoice) {
            case ("1") -> {
                makeCoffee(ESPRESSO_WATER_PER_CUP, ESPRESSO_MILK_PER_CUP, ESPRESSO_COFFEE_PER_CUP);
                processPayment(ESPRESSO_PRICE);
            }
            case ("2") -> {
                makeCoffee(LATTE_WATER_PER_CUP, LATTE_MILK_PER_CUP, LATTE_COFFEE_PER_CUP);
                processPayment(LATTE_PRICE);
            }
            case ("3") -> {
                makeCoffee(CAPPUCCINO_WATER_PER_CUP, CAPPUCCINO_MILK_PER_CUP, CAPPUCCINO_COFFEE_PER_CUP);
                processPayment(CAPPUCCINO_PRICE);
            }
            case ("back") -> {

            }
            default -> {
            }
        }
    }

    private static void makeCoffee(int water, int milk, int coffee) {
        if (waterAviable < water) {
            System.out.println("Sorry, not enough water!");
            return;
        }

        if (milkAviable < milk) {
            System.out.println("Sorry, not enough milk!");
            return;
        }

        if (coffeeAviable < coffee) {
            System.out.println("Sorry, not enough coffee bean!");
            return;
        }

        if (cupsAviable < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }

        waterAviable -= water;
        milkAviable -= milk;
        coffeeAviable -= coffee;
        cupsAviable--;

        System.out.println("I have enough resources, making you a coffee!");
    }
    private static void processPayment(int price) {
        moneyAviable += price;
    }
    private static void getTake() {
        System.out.printf("I gave you $%d\n", moneyAviable);
        moneyAviable = 0;
    }

    private static int getCalculations(int waterAviable, int milkAviable, int coffeeAviable) {
        int cupsWater = waterAviable / 200;
        int cupsMilk = milkAviable / 50;
        int cupsCoffee = coffeeAviable / 15;
        return Math.min(cupsWater, Math.min(cupsCoffee,cupsMilk));
    }

    private static void getAviableCalculations(int waterAviable, int milkAviable, int coffeeAviable, int cupsAviable, int moneyAviable ) {
        System.out.printf("The coffee machine has:\n");
        System.out.printf("%d ml of water\n", waterAviable);
        System.out.printf("%d ml of milk\n", milkAviable);
        System.out.printf("%d g of coffee beans\n", coffeeAviable);
        System.out.printf("%d disposable cups\n", cupsAviable);
        System.out.printf("$%d of money\n\n", moneyAviable);
    }
}
