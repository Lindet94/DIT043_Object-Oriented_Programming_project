package Menus;
import Utility.Prints;
import Utility.UserInput;
import facade.Facade;
import java.util.*;

public class ReviewOptions {

    MainMenu getFacade = new MainMenu();
    public Facade facade = getFacade.getFacade();

    public void reviewOptions() {

        Scanner input = new Scanner(System.in);
        String menuOption;

        do {
            Prints.reviewOptions();
            menuOption = input.nextLine();

            switch (menuOption) {
                case "0":
                    MainMenu.mainMenu();
                case "1": {
                    String itemID = UserInput.readLine("Enter the ID of the item you would like to review:");
                    int reviewGrade = UserInput.readInt("What grade between 1-5?");
                    String reviewComment = UserInput.readLine("What comment?");
                    String reviewItem = facade.reviewItem(itemID, reviewComment, reviewGrade);
                    System.out.println(reviewItem);
                }
                    break;
                case "2": {
                    String itemID = UserInput.readLine("Please specify the item ID of the desired review: ");
                    int reviewNumber = UserInput.readInt("Please specify the index: ");
                    String getPrintedItemReview = facade.getPrintedItemReview(itemID, reviewNumber);
                    System.out.println(getPrintedItemReview);
                }
                    break;
                case "3": {
                    String itemID = UserInput.readLine("Please specify the item ID: ");
                    String getPrintedReviews = facade.getPrintedReviews(itemID);
                    System.out.println(getPrintedReviews);
                }
                    break;
                case "4": {
                    String itemID = UserInput.readLine("Enter the ID of the item you would like the review mean of: ");
                    double totalMean = facade.getItemMeanGrade(itemID);
                    if (totalMean != -1) {
                        System.out.println("The total mean for " + itemID + " is " + totalMean);
                    } else {
                        System.out.println("Item" + itemID + "was not registered yet.");
                    }
                }
                    break;
                case "5": {
                    String itemID = UserInput.readLine("Enter the ID of the item you would like to retrieve the comments of");
                    List getItemComments = facade.getItemComments(itemID);
                    System.out.println(getItemComments);
                }
                    break;
                case "6": {
                    String printAllReviews = facade.printAllReviews();
                    System.out.println(printAllReviews);
                }
                    break;
                case "7": {
                    String printMostReviewedItems = facade.printMostReviewedItems();
                    System.out.println(printMostReviewedItems);
                }
                    break;
                case "8": {
                    String printLeastReviewedItems = facade.printLeastReviewedItems();
                    System.out.println(printLeastReviewedItems);
                }
                    break;
                case "9": {
                    String printBestReviewedItems = facade.printBestReviewedItems();
                    System.out.println(printBestReviewedItems);
                }
                    break;
                case "10": {
                    String printWorseReviewedItems = facade.printWorseReviewedItems();
                    System.out.println(printWorseReviewedItems);
                }
                    break;
                default:
                    Prints.invalidOption();
            }
        }
        while (!(menuOption.equals("0")));

    }
}
