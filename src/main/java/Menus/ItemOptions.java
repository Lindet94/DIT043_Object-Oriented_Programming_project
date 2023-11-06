package Menus;
import facade.Facade;
import Utility.Prints;
import Utility.UserInput;
import java.util.Scanner;

public class ItemOptions {

   MainMenu getFacade = new MainMenu();
   public Facade facade = getFacade.getFacade();

   public void itemOptions() {

      Scanner input = new Scanner(System.in);
      String menuOption;

      do {
         Prints.itemOptions();
         menuOption = input.nextLine();
         switch (menuOption) {
            case "0":
               MainMenu.mainMenu();
               break;
            case "1": {
               String itemID = UserInput.readLine("Please enter the item ID: ");
               String itemName = UserInput.readLine("Please enter the item name: ");
               double unitPrice = UserInput.readDouble("Please enter the item price");
               String createItem = facade.createItem(itemID, itemName, unitPrice);
               System.out.println(createItem);
            }
               break;
            case "2": {
               String itemID = UserInput.readLine("Please write the ID of the Item that you would like to remove: ");
               String removeItem = facade.removeItem(itemID);
               System.out.println(removeItem);
            }
               break;
            case "3": {
               String printAllItems = facade.printAllItems();
               System.out.println(printAllItems);
            }
               break;
            case "4": {
               String itemID = UserInput.readLine("Please enter the ID of the Item you would like to buy: ");
               int amount = UserInput.readInt("Please enter the amount of items you would like to buy: ");
               double buyItem = facade.buyItem(itemID, amount);
               System.out.println(buyItem);
            }
               break;
            case "5": {
               String inputID = UserInput.readLine(" Please write the ID ");
               String inputName = UserInput.readLine(" Please write the new name ");
               String updateItemName = facade.updateItemName(inputID, inputName); // Ropar på facade metoden "updateItemName"
               System.out.println(updateItemName);
            }
               break;
            case "6": {
               String inputID = UserInput.readLine(" Please write the ID ");
               double inputPrice = UserInput.readDouble("Please write the new price ");
               String updateItemPrice = facade.updateItemPrice(inputID, inputPrice);
               System.out.println(updateItemPrice);
            }
               break;
            case "7": {
               String idToPrint = UserInput.readLine("Type id of item to print:");
               String printItem = facade.printItem(idToPrint);
               System.out.println(printItem);
            }
               break;
            default:
               Prints.invalidOption();
         }
      } while (!(menuOption.equals("0")));
      UserInput.closeScanner();
   }
}
