package Menus;

import Utility.Prints;
import Utility.UserInput;
import facade.*;

import java.util.Scanner;

public class MainMenu {

   public static Facade facade = new Facade(); // För att använda samma facade hela tiden

   public static void mainMenu() {

      Scanner input = new Scanner(System.in);

      String menuOption;
      do {
         Prints.mainMenu();
         menuOption = input.nextLine();
         switch (menuOption) {
            case "0":
               System.out.println("Closing program.");
               System.exit(0);
               break;
            case "1":
               new ItemOptions().itemOptions();
               break;
            case "2":
               new ReviewOptions().reviewOptions();
               break;
            case "3":
               new TransactionHistory().transactionHistory();
               break;
            case "4":
               new EmployeeMenu().employeeMenu();
            default:
               Prints.invalidOption();
         }
      } while (!(menuOption.equals("0")));
      UserInput.closeScanner();
   }
   public Facade getFacade() {
      return facade;
   }
}