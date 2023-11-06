package Utility;

public class Prints {

    static final String EOL = System.lineSeparator();

    public static void mainMenu(){
        System.out.println("Main Menu: Please choose among the options below." + EOL + "0. Close system." + EOL +
                "1. Open Item options." + EOL + "2. Open Review options." + EOL + "3. Open Transaction history." + EOL + "4. Open Employee options." + EOL +
                "Type an option number: ");
    }
    public static void itemOptions(){
        System.out.println("Item options menu:" + EOL + "0. Return to Main Menu." + EOL + "1. Create an item." + EOL + "2. Remove an Item." + EOL +
                "3. Print all registered Items." + EOL + "4. Buy an Item." + EOL + "5. Update an item's name." + EOL +
                "6. Update an item's price." + EOL + "7. Print a specific item. ");
    }
    public static void reviewOptions(){
        System.out.println("Reviews options menu:" + EOL +
                "0. Return to Main Menu." + EOL +
                "1. Create a review for an Item." + EOL +
                "2. Print a specific review of an Item." + EOL +
                "3. Print all reviews of an Item." + EOL +
                "4. Print mean grade of an Item" + EOL +
                "5. Print all comments of an Item." + EOL +
                "6. Print all registered reviews." + EOL +
                "7. Print item(s) with most reviews." + EOL +
                "8. Print item(s) with least reviews." + EOL +
                "9. Print item(s) with best mean review grade." + EOL +
                "10. Print item(s) with worst mean review grade." + EOL +
                EOL +
                "Type an option number:"+ EOL);
    }
    public static void transactionHistory(){
        System.out.println("Transaction History options menu:" + EOL +
                "0. Return to Main Menu." + EOL +
                "1. Print total profit from all item purchases" + EOL +
                "2. Print total units sold from all item purchases" + EOL +
                "3. Print the total number of item transactions made." + EOL +
                "4. Print all transactions made." + EOL +
                "5. Print the total profit of a specific item." + EOL +
                "6. Print the number of units sold of a specific item." + EOL +
                "7. Print all transactions of a specific item." + EOL +
                "8. Print item with highest profit." + EOL +
                "Type an option number:");
    }
    public static void employeeMenu(){
        System.out.println("Employee options menu:" + EOL + "0. Return to Main Menu." + EOL +
                "1. Create an employee (Regular Employee)." + EOL + "2. Create an employee (Manager)." + EOL +
         "3. Create an employee (Director)." + EOL + "4. Create an employee (Intern)." + EOL +
                "5. Remove an employee." + EOL + "6. Print specific employee." + EOL +
                "7. Print all registered employees." + EOL + "8. Print the total expense with net salary." + EOL +
                "9. Print all employees sorted by gross salary.");
    }

    public static void invalidOption(){
        System.out.println("Invalid menu option. Please type another option." + EOL);
    }
}
