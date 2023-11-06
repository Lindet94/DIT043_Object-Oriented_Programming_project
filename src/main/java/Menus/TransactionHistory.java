package Menus;
import Utility.Prints;
import Utility.UserInput;
import facade.Facade;
import java.util.Scanner;

public class TransactionHistory {

    MainMenu getFacade = new MainMenu(); // fix för att hänvisa till samma facade varje gång
    public Facade facade = getFacade.getFacade();
    public void transactionHistory() {

        Scanner input = new Scanner(System.in);

        String menuOption;
        do {
            Prints.transactionHistory();
            menuOption = input.nextLine();
            switch (menuOption) {
                case "0":
                    MainMenu.mainMenu();
                case "1":
                    double totalProfit = facade.getTotalProfit();
                    System.out.println(totalProfit);
                    break;
                case "2":
                    int totalUnitsSold = facade.getTotalUnitsSold();
                    System.out.println(totalUnitsSold);
                    break;
                case "3":
                    int totalTransactions = facade.getTotalTransactions();
                    System.out.println(totalTransactions);
                    break;
                case "4":
                    String printTransactions = facade.printAllTransactions();
                    System.out.println(printTransactions);
                    break;
                case "5": {
                    String itemID = UserInput.readLine("Please input the item ID:");
                    double printProfit = facade.getProfit(itemID);
                    System.out.println(printProfit);
                }
                    break;
                case "6": {
                    String itemID = UserInput.readLine("Please input the item ID:");
                    int printUnitsSold = facade.getUnitsSolds(itemID);
                    System.out.println(printUnitsSold);
                }
                    break;
                case "7": {
                    String itemID = UserInput.readLine("Please input the item ID:");
                    String printItemTransactions = facade.printItemTransactions(itemID);
                    System.out.println(printItemTransactions);
                }
                    break;
                case "8":
                    String printMostProfitAbleItem = facade.printMostProfitableItems();
                    System.out.println(printMostProfitAbleItem);
                    break;
                default:
                    Prints.invalidOption();
            }

        } while (!(menuOption.equals("0")));
        UserInput.closeScanner();
    }
}