package facade;
import Objects.*;
import java.util.*;


public class Facade {

    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.
    public ArrayList<Item> itemList; // ändra till transactionLists
    public ArrayList<Transaction> transactionList; // ändra till transactionsList
    public ArrayList<Employee> employeeList;
    static final String EOL = System.lineSeparator();

    public Facade() {
        itemList = new ArrayList<>();
        this.transactionList= new ArrayList<>();
        this.employeeList = new ArrayList<>();
    }

    public String createItem(String itemID, String itemName, double unitPrice) {
        String operationResult;
        if (containsItem(itemID)) {
            operationResult = "Item ID already exists";
        } else if (itemID.isBlank() || itemName.isBlank() || unitPrice <= 0.0) {
            operationResult = "Invalid data for item.";
        } else {
            Item item = new Item(itemID, itemName, unitPrice);
            itemList.add(item);
            operationResult = "Item " + item.getId() + " was registered successfully.";
        }
        return operationResult;
    }
    public String printItem(String itemID) {
        String operationResult;
        Item itemToPrint = null;
        for (Item currentItem : itemList) {
            if (currentItem.getId().equals(itemID)) {
                itemToPrint = currentItem;
            }
        }
        if (itemToPrint == null) {
            operationResult = "Item " + itemID + " was not registered yet.";
        } else {
            operationResult = itemToPrint.toString();
        }
        return operationResult;
    }
    public String removeItem(String itemID) {
        Item tobeRemoved = findItem(itemID);
        if (tobeRemoved != null) {
            itemList.remove(tobeRemoved);
            return "Item " + itemID + " was successfully removed.";
        } else {
            return "Item " + itemID + " could not be removed.";
        }
    }

    public boolean containsItem(String itemID) {
        for (Item item : itemList) {
            if (item.getId().equals(itemID)) {
                return true;
            }
        }
        return false;
    }

    public Item findItem(String itemID) {
        if (itemList.size() > 0) {
            for (Item item : itemList) {
                if (item.getId().equals(itemID)) {
                    return item;
                }
            }
        }
        return null;
    }

    public boolean containsTransaction(String itemID) {
        for (Transaction transaction : transactionList) {
            if (transaction.getId().equals(itemID)) {
                return true;
            }
        }
        return false;
    }

    public double buyItem(String itemID, int amount) {
        Item transactionItem = findItem(itemID);
        double totalAmount = -1;
        double totalPrice;
        if (transactionItem != null) {
            if (amount <= 4) {
                totalPrice = amount * transactionItem.getPrice();
                totalAmount = totalPrice;
                totalAmount = truncateTo(totalAmount, 2);
            } else {
                double discountItems = amount - 4.0;
                totalPrice = 4 * (transactionItem.getPrice()) + (discountItems * transactionItem.getPrice() * (1 - 0.3));
                totalAmount = totalPrice;
                totalAmount = truncateTo(totalAmount, 2);
            }
            Transaction newTransaction = new Transaction(itemID, amount, totalPrice);
            transactionList.add(newTransaction);
        }
        return totalAmount;
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        Item reviewItem = findItem(itemID);
        if (reviewItem == null) {
            return "Item " + itemID + " was not registered yet.";
        } else if (reviewGrade <= 0 || reviewGrade > 5) {
            return "Grade values must be between 1 and 5.";
        } else {
            reviewItem.newReview(reviewGrade, reviewComment);
            return "Your item review was registered successfully.";
        }
    }

    public String reviewItem(String itemID, int reviewGrade) {
        String operationsResult;
        Item reviewItem = findItem(itemID);
        if (reviewItem != null) {
            if (reviewGrade <= 0 || reviewGrade > 5) {
                operationsResult = "Grade values must be between 1 and 5.";
                return operationsResult;
            }
            reviewItem.newReview2(reviewGrade, itemID);
            operationsResult = "Your item review was registered successfully.";
            return operationsResult;
        } else {
            operationsResult = "Item " + itemID + " was not registered yet.";
            return operationsResult;
        }
    }

    public String getItemCommentsPrinted(String itemID) {
        String comments = "";
        String operationsResult;
        Item itemComments = findItem(itemID);
        if (itemComments != null) {
            if (itemComments.reviewList.size() < 1) {
                operationsResult = "Item " + itemComments.getId() + " has not been reviewed yet.";
            }
            for (Review comment : itemComments.reviewList) {
                comment.getComment(comment.comment);
                if (comment.comment != null && !comment.comment.isEmpty()) {
                    comments = (comment.comment);
                }
            }
            return comments;
        } else {
            return comments;
        }
    }

    public List<String> getItemComments(String itemID) {
        List<String> comments = new ArrayList<>();
        Item itemComments = findItem(itemID);
        if (itemComments != null) {
            if (itemComments.reviewList.size() < 1) {
            }
            for (Review comment : itemComments.reviewList) {
                comment.getComment(comment.comment);
                if ((comment.comment != null && !comment.comment.isEmpty())){
                    comments.add(comment.comment);
                }
            }
            return comments;
        }
        return comments;
    }

    public double getItemMeanGrade(String itemID) {
        int numberOfItems = 0;
        int decimals = 1;
        double sum = 0;
        double avg = 0;
        Item itemMean = findItem(itemID);
        if (itemMean != null) {
            for (Review reviews : itemMean.reviewList) {
                numberOfItems++;
                double allGrades = reviews.getGrade(reviews.grade);
                sum+= allGrades;
            }
            avg = sum/numberOfItems;
            avg = truncateTo(avg, decimals);
            return avg;
        }
        return avg;
    }

    public int getNumberOfReviews(String itemID) {

        Item findReviews = findItem(itemID);
        int getNumberOfReviews = 0;
        for (Review ignored : findReviews.reviewList) {
            truncateTo(getNumberOfReviews, 1);
            getNumberOfReviews++;
        }
        return getNumberOfReviews;
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {

        String operationsResult;
        Item item = findItem(itemID);
        if (item == null) {
            return "Item " + itemID + " was not registered yet.";
        } else if (item.reviewList.size() > 0) {

            while (reviewNumber < 1 || reviewNumber > item.reviewList.size()) {
                return "Invalid review number. Choose between 1 and " + item.reviewList.size() + ".";
            }
            operationsResult = item.reviewList.get(reviewNumber - 1).toString();
            return operationsResult;
        } else {
            return "Item " + item.getName() + " has not been reviewed yet.";
        }
    }

    public String getPrintedReviews(String itemID) {

        Item item7 = findItem(itemID);
        if (item7 != null) {
            if (item7.reviewList.size() < 1) {
                return "Review(s) for " + item7 + EOL + "The item " + item7.getName() + " has not been reviewed yet.";
            }
            String itemResult = "Review(s) for " + item7;
            for (Review reviews : item7.reviewList) {
                itemResult += EOL + reviews;
            }
            return itemResult + EOL;
        }
        return "Item " + itemID + " was not registered yet.";
    }

    public String printMostReviewedItems() {
        // Could just utilize the method (getMostReviewedItems) for finding the "Most" in this case.
        if (itemList.isEmpty()) {
            return "No items registered yet.";
        }
        int mostReviews = 0; // För minsta möjliga Integer.MAX_VALUE
        List<Item> printItems = new ArrayList<>();

        for (Item item : itemList) {
            if (mostReviews < item.getNumberOfReviews()) { // > för minsta
                mostReviews = item.getNumberOfReviews();
                printItems.clear(); // tömmer listan "printItems"
                printItems.add(item); // Lägger till OM det är större än föregående
            } else if (mostReviews == item.getNumberOfReviews()) { // Metod för att lägga till == i listan
                printItems.add(item);
            }
        }
        if (mostReviews == 0) {
            return "No items were reviewed yet.";
        }
        String reviewResult = "Most reviews: " + mostReviews + " review(s) each." + EOL;
        for (Item printItem : printItems) {
            reviewResult += printItem + EOL;
        }
        return reviewResult;

    }

    public String truncateToString(double unroundedNumber, int decimalPlaces) {

        return String.format("%." + decimalPlaces + "f", unroundedNumber);
    }

    public List<String> getMostReviewedItems() {

        List<String> mostReviewedItems = new ArrayList<>();
        if (itemList.isEmpty()) {
            return mostReviewedItems;
        }
        int mostReviews = 0; // För minsta möjliga Integer.MAX_VALUE

        for (Item item : itemList) {
            if (item.getNumberOfReviews() == 0) {
                continue;
            }
            if (mostReviews < item.getNumberOfReviews()) { // > för minsta
                mostReviews = item.getNumberOfReviews();
                mostReviewedItems.clear(); // tömmer listan "mostReviewedItems"
                mostReviewedItems.add(item.getId()); // Lägger till OM det är större än föregående
            } else if (mostReviews == item.getNumberOfReviews()) { // Metod för att lägga till == i listan
                mostReviews = item.getNumberOfReviews();
                mostReviewedItems.add(item.getId());
            }
            if(mostReviewedItems.isEmpty()){
                return mostReviewedItems;
            }
        }
        return mostReviewedItems;
    }

    public List<String> getLeastReviewedItems() {

        List<String> leastReviewedItems = new ArrayList<>();

        if (itemList.isEmpty()) {
            return leastReviewedItems;
        }
        int leastReviews = Integer.MAX_VALUE; // För minsta möjliga Integer.MAX_VALUE

        for (Item item : itemList) {

            if (item.getNumberOfReviews() == 0) {
                continue;
            }
            if (leastReviews > item.getNumberOfReviews()) { // > för minsta
                leastReviews = item.getNumberOfReviews();
                leastReviewedItems.clear(); // tömmer listan "printItems"
                leastReviewedItems.add(item.getId()); // Lägger till OM det är mindre än föregående
            } else if (leastReviews == item.getNumberOfReviews()) { // Metod för att lägga till == i listan
                leastReviews = item.getNumberOfReviews();
                leastReviewedItems.add(item.getId());
            }
        }
        if (leastReviewedItems.isEmpty()) {
            return leastReviewedItems;
        }
        return leastReviewedItems;
    }

    public String printLeastReviewedItems() {

        if (itemList.isEmpty()) {
            return "No items registered yet.";
        }
        int leastReviews = Integer.MAX_VALUE;
        List<Item> printItems = new ArrayList<>();

        for (Item item : itemList) {

            if (item.getNumberOfReviews() == 0) {
                continue; // Struntar att kolla på itemet om review.size = 0 (inte har någon review på sig) och fortsätter for loopen.
            }
            if (leastReviews > item.getNumberOfReviews()) {

                leastReviews = item.getNumberOfReviews();
                printItems.clear(); // tömmer listan "printItems"
                printItems.add(item); // Lägger till OM det är mindre än föregående
            } else if (leastReviews == item.getNumberOfReviews()) { // Metod för att lägga till == i listan
                printItems.add(item);

            }
        }
        if (leastReviews == Integer.MAX_VALUE) { // Då har det inte förändrats.
            return "No items were reviewed yet.";
        }
        String reviewResult = "Least reviews: " + leastReviews + " review(s) each." + EOL;

        for (Item printItem : printItems) {
            reviewResult += printItem + EOL;
        }
        return reviewResult;
    }

    public double getTotalProfit() {
        double totalProfit = 0.0;
        for (Transaction transaction : transactionList) {
            totalProfit += transaction.getTotalPricePaid();
        }
        totalProfit = truncateTo(totalProfit, 2);
        return totalProfit;
    }

    public String printItemTransactions(String itemID) {
        String message;
        if (!containsItem(itemID)) {
            message = "Item " + itemID + " was not registered yet.";
        } else if (!containsTransaction(itemID)) {
            message = ("Transactions for item: " + findItem(itemID) + EOL + "No transactions have been registered for item " + itemID + " yet.");
        } else {
            message = "Transactions for item: " + findItem(itemID) + EOL;
            for (Transaction transaction : transactionList) {
                if (transaction.getId().equals(itemID)) {
                    message += transaction + EOL;
                }
            }
        }
        return message;
    }

    public int getTotalUnitsSold() {
        int totalUnits = 0;
        for (Transaction transaction : transactionList) {
            totalUnits += transaction.getUnitsBought();
        }
        return totalUnits;
    }

    public int getTotalTransactions() {
        int totalTransactions = transactionList.size();
        if (transactionList.size() > 0) {
            return totalTransactions;
        }
        return totalTransactions;
    }

    public double getProfit(String itemID) {
        double totalPrice = 0.0;
        for (Transaction transaction : transactionList) {
            if (transaction.getId().equals(itemID)) {
                totalPrice += transaction.getTotalPricePaid();
            }
            totalPrice = truncateTo(totalPrice, 2);
        }
        return totalPrice;
    }

    public int getUnitsSolds(String itemID) {
        int sumOfUnits = 0;
        for (Transaction transaction : transactionList) {
            if (transaction.getId().equals(itemID)) {
                sumOfUnits = sumOfUnits + transaction.getUnitsBought();
            }
        }
        return sumOfUnits;
    }

    public String printAllTransactions() {
        if (transactionList.size() == 0) {
            return ("All purchases made: " + EOL + "Total profit: 0.00 SEK" + EOL +
                    "Total items sold: 0 units" + EOL + "Total purchases made: 0 transactions" + EOL +
                    "------------------------------------" + EOL + "------------------------------------") + EOL;
        } else {
            String allTransactions = ("All purchases made: " + EOL + "Total profit: " + getTotalProfit() + " SEK" + EOL +
                    "Total items sold: " + getTotalUnitsSold() + " units" + EOL + "Total purchases made: " +getTotalTransactions() + " transactions" +
                    EOL + "------------------------------------" + EOL);
            for (Transaction transaction : transactionList) {
                allTransactions += transaction + EOL;
            }
            return allTransactions + "------------------------------------" + EOL;
        }
    }

    public String printWorseReviewedItems() {

        if (itemList.isEmpty()) {
            return "No items registered yet.";
        }
        double worstReviews = Integer.MAX_VALUE;
        List<Item> printItems = new ArrayList<>();

        for (Item item : itemList) {

            if (calculateMean1Decimal(item) == 0) {
                continue; // Struntar att kolla på itemet om review.size = 0 (inte har någon review på sig) och fortsätter for loopen.
            }
            if (worstReviews > calculateMean1Decimal(item)) {
                worstReviews = calculateMean1Decimal(item);
                printItems.clear(); // tömmer listan "printItems"
                printItems.add(item); // Lägger till OM det är mindre än föregående
            } else if (worstReviews == calculateMean1Decimal(item)) { // Metod för att lägga till == i listan
                printItems.add(item);

            }
        }
        if (worstReviews == Integer.MAX_VALUE) { // Då har det inte förändrats.
            return "No items were reviewed yet.";
        }
        String reviewResult = "Items with worst mean reviews:" + EOL + "Grade: " + worstReviews + EOL;

        for (Item printItem : printItems) {
            reviewResult += printItem + EOL;
        }
        return reviewResult;

    }


    public String printBestReviewedItems() {

        if (itemList.isEmpty()) {
            return "No items registered yet.";
        }
        double bestReview = 0;
        List<Item> printItems = new ArrayList<>();
        for (Item item : itemList) {
            if (calculateMean1Decimal(item) == 0) {
                continue;
            }
            if (bestReview < calculateMean1Decimal(item)) {
                bestReview = calculateMean1Decimal(item);
                printItems.clear();
                printItems.add(item);
            } else if (bestReview == calculateMean1Decimal(item)) {
                printItems.add(item);
            }
        }
        if (bestReview == 0){
        return "No items were reviewed yet.";
        }
        String reviewResult = "Items with best mean reviews:" + EOL + "Grade: " + bestReview + EOL;
        for (Item printItem : printItems) {
            reviewResult += printItem + EOL;
        }
        return reviewResult;
    }

    public List<String> getWorseReviewedItems() {
        List<String> worstReviewedItems = new ArrayList<>();
        if (itemList.isEmpty()) {
            return worstReviewedItems;
        }
        double worstReviews = Integer.MAX_VALUE; // För minsta möjliga Integer.MAX_VALUE
        for (Item item : itemList) {
            if (calculateMean1Decimal(item) == 0) {
                continue;
            }
            if (worstReviews > calculateMean1Decimal(item)) {  // Går genom klassen Item
                worstReviews = calculateMean1Decimal(item);
                worstReviewedItems.clear(); // tömmer listan "printItems"
                worstReviewedItems.add(item.getId()); // Lägger till OM det är mindre än föregående
            } else if (worstReviews == calculateMean1Decimal(item)) { // Metod för att lägga till == i listan
                worstReviewedItems.add(item.getId());
            }
        }
        return worstReviewedItems;
    }

    public List<String> getBestReviewedItems() {
        List<String> bestReviewedItems = new ArrayList<>();
        if (itemList.isEmpty()) {
            return bestReviewedItems;
        }
        double bestReviews = 0; // För minsta möjliga Integer.MAX_VALUE
        for (Item item : itemList) {
                if(calculateMean1Decimal(item) == 0) {
                    continue;
                }
            if (bestReviews < calculateMean1Decimal(item)) {  // Går genom klassen Item
                bestReviews = calculateMean1Decimal(item);
                bestReviewedItems.clear(); // tömmer listan "printItems"
                bestReviewedItems.add(item.getId()); // Lägger till OM det är större än föregående
            } else if (bestReviews == calculateMean1Decimal(item)) { // Metod för att lägga till == i listan
                bestReviewedItems.add(item.getId());
            }
        }
        return bestReviewedItems;
    }

    public static double truncateTo(double unroundedNumber, int decimalPlaces) {
        int truncatedNumberInt = (int) (unroundedNumber * Math.pow(10, decimalPlaces));
        return (truncatedNumberInt / Math.pow(10, decimalPlaces));
    }


    public String printAllReviews() {

        if (itemList.isEmpty()) {
            return "No items registered yet.";
        }

        String itemToPrint = "";


        for (Item item : itemList) {

            if (item.getNumberOfReviews() == 0) {
                continue; // Fortsätter loopen fastän den hittar ett item med 0 reviews på sig.
            }
            itemToPrint = itemToPrint + "------------------------------------" + EOL + "Review(s) for " + item.toString() + EOL;

            for (Review review : item.reviewList) {

                itemToPrint = itemToPrint + review.toString() + EOL;

            }
        }
        if(itemToPrint.isEmpty()){
            return "No items were reviewed yet.";
        }
        return "All registered reviews:" + EOL + itemToPrint + "------------------------------------" + EOL;
    }

    public String updateItemName(String itemID, String newName) {

        String operationResult = "";
        Item itemToName = null;
        for (Item currentItem : itemList) {
            if (currentItem.getId().equals(itemID)) {
                if (newName.isEmpty()) {
                    return "Invalid data for item.";
                }
                itemToName = currentItem;
                currentItem.setName(newName);
                operationResult = "Item " + itemID + " was updated successfully.";
            }
        }

        if (itemToName == null) {
            operationResult = "Item " + itemID + " was not registered yet.";
            return operationResult;
        }
        return operationResult;

    }

    public String updateItemPrice(String itemID, double newPrice) {
        String operationResult = "";
        Item itemToPrice = null;
        for (Item currentItem : itemList) {
            if (currentItem.getId().equals(itemID)) {
                if (newPrice <= 0) {
                    return "Invalid data for item.";
                }
                itemToPrice = currentItem;
                currentItem.setPrice(newPrice);
                operationResult = "Item " + itemID + " was updated successfully.";
            }
        }

        if (itemToPrice == null) {
            operationResult = "Item " + itemID + " was not registered yet.";
            return operationResult;
        }
        return operationResult;

    }


    public String printAllItems() {
        if (itemList.isEmpty()) {
            return "No items registered yet.";
        }
            String allItems = "All registered items:";

        for (Item item : itemList) {
            allItems = allItems + EOL + item.toString();
        }
        return allItems + EOL;
    }

    public String printMostProfitableItems() {

        if (itemList.isEmpty()) {
            return "No items registered yet.";
        }
        double mostProfitItem = 0.0;

        List<Integer> printProfits = new ArrayList<>();
        List<Double> sumPerItemType = new ArrayList<>();


        for(int i = 0 ; i<itemList.size(); i++){
            sumPerItemType.add(0.0);
            for (Transaction transaction : transactionList) {
                if(transaction.getId().equals(itemList.get(i).getId())) {   // refererar till item på index (i). För varje varv så blir i > i+1 i++ = i+1
                    sumPerItemType.set(i, transaction.getTotalPricePaid() + sumPerItemType.get(i));

                }
            }
        }
        for(int i = 0 ; i<sumPerItemType.size(); i++){
            if (mostProfitItem < sumPerItemType.get(i)) {
                mostProfitItem = sumPerItemType.get(i);
                printProfits.clear();
                printProfits.add(i);
            } else if (mostProfitItem == sumPerItemType.get(i)) {
                mostProfitItem = sumPerItemType.get(i);
                printProfits.add(i);
            }
        }

        if (mostProfitItem == 0.0) {
            return "No items were bought yet.";
        }
        String printResult = "Most profitable items: "+ EOL + "Total profit: " + truncateToString(mostProfitItem, 2) + " SEK" + EOL;
        for (int printProfit : printProfits){
            printResult += itemList.get(printProfit).toString() + EOL;
        }
        return printResult;
    }

    //RegularEmployee
    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        if (employeeID.isBlank()){
            throw new Exception("ID cannot be blank.");
        } else if (employeeName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        } else if (grossSalary < 0) {
            throw new Exception("Salary must be greater than zero.");
        }
        grossSalary = truncateTo(grossSalary,2);
        Employee regularEmployee = new RegularEmployee(employeeID, employeeName, grossSalary);
        employeeList.add(regularEmployee);
        return "Employee " + employeeID + " was registered successfully.";
    }


    public String printEmployee(String employeeID) throws Exception {
        Employee employeeToPrint = null;
        for(Employee currentEmployee : employeeList){
            if(currentEmployee.getId().equals(employeeID)){
                employeeToPrint = currentEmployee;
            }
        }
        if(employeeToPrint == null) throw new Exception("Employee " + employeeID + " was not registered yet.");

        return employeeToPrint.toString();
    }

    // Manager
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        if (employeeID.isBlank()){
            throw new Exception("ID cannot be blank.");
        } else if (employeeName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        } else if (grossSalary < 0) {
            throw new Exception("Salary must be greater than zero.");
        } else if (!degree.equals("PhD") && !degree.equals("MSc") && !degree.equals("BSc")) {
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }
        grossSalary = truncateTo(grossSalary, 2);
        Employee manager = new Manager(employeeID, employeeName, grossSalary, degree);
        employeeList.add(manager);
        return "Employee " + employeeID + " was registered successfully.";
    }

    //Intern
    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        if (employeeID.isBlank()){
            throw new Exception("ID cannot be blank.");
        } else if (employeeName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        } else if (grossSalary < 0) {
            throw new Exception("Salary must be greater than zero.");
        } else if (gpa < 0 || gpa > 10) {
            throw new Exception(gpa + " outside range. Must be between 0-10.");
        }
        grossSalary = truncateTo(grossSalary, 2);
        Employee intern = new Intern(employeeID, employeeName, grossSalary, gpa);
        employeeList.add(intern);
        return "Employee " + employeeID + " was registered successfully.";
    }

    public double getNetSalary(String employeeID) throws Exception {
        double netSalary;
            Employee findEmployee = findEmployee(employeeID);
            if (findEmployee == null) {
                throw new Exception("Employee " + employeeID + " was not registered yet.");
            } else {
                netSalary = findEmployee.getNetSalary();
            }
        truncateTo(netSalary, 2);
        return netSalary;
    }

    //Director
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        if (employeeID.isBlank()){
            throw new Exception("ID cannot be blank.");
        } else if (employeeName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        } else if (grossSalary < 0) {
            throw new Exception("Salary must be greater than zero.");
        } else if (!degree.equals("BSc") && !degree.equals("MSc") && !degree.equals("PhD")) {
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        } else if (!dept.equals("Business") && !dept.equals("Human Resources") && !dept.equals("Technical")) {
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }
        grossSalary = truncateTo(grossSalary, 2);
            Employee director = new Director(employeeID, employeeName, grossSalary, degree, dept);
            employeeList.add(director);
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String removeEmployee(String empID) throws Exception{
            Employee toBeRemoved = findEmployee(empID);
            if (toBeRemoved == null) {
                throw new Exception("Employee " + empID + " was not registered yet.");
            } else {
                employeeList.remove(toBeRemoved);
            }
            return "Employee " + empID + " was successfully removed.";
        }

    public double calculateMean1Decimal(Item item) {
        int numberOfItems = 0;
        int decimals = 1;
        double sum = 0;
        for (Review reviews : item.reviewList) {
            numberOfItems++;
            double allGrades = reviews.getGrade(reviews.grade);
            sum += allGrades;
        }
        double avg = sum / numberOfItems;
        avg = truncateTo(avg, decimals);
        return avg;
    }


    public String printAllEmployees() throws Exception {
        String employeeToPrint = "";
        if(employeeList.isEmpty()) {
            throw new Exception("No employees registered yet.");
        }
        else{
        for(Employee employee : employeeList)
            employeeToPrint = employeeToPrint + employee + EOL;
        }
        return "All registered employees:"+ EOL + employeeToPrint;
    }

    public double getTotalNetSalary() throws Exception {

        double totalNetSalary = 0;

        if (employeeList.isEmpty()) {
            throw new Exception("No employees registered yet.");
        } else{
            for (Employee employee : employeeList) {
                employee.getNetSalary();
                totalNetSalary += employee.getNetSalary();
            }
            return truncateTo(totalNetSalary, 2);
        }
    }

    public String printSortedEmployees() throws Exception {
        String employeeToPrint = "";

        if(employeeList.isEmpty()){
            throw new Exception("No employees registered yet.");
        }
        employeeList.sort(Comparator.comparing(Employee::getGrossSalary));
        for(Employee employee : employeeList) {

            employeeToPrint = employeeToPrint + employee + EOL;
        }

        return "Employees sorted by gross salary (ascending order):" + EOL + employeeToPrint;
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        Employee employeeToRename = findEmployee(empID);
        if (employeeToRename == null) {
            throw new Exception("Employee " + empID + " was not registered yet.");
        } else if (newName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        } else {
            employeeToRename.setName(newName);
            }
        return "Employee " + empID + " was updated successfully";
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        Employee employee = findEmployee(empID);
        Intern updateInternGPA;
        if (employee == null) {
            throw new Exception("Employee " + empID + " was not registered yet.");
        } else if (newGPA < 0 || newGPA > 10) {
            throw new Exception(newGPA + " outside range. Must be between 0-10.");
        } else {
            updateInternGPA = (Intern) employee;
            updateInternGPA.setGpa(newGPA);
        }
        return "Employee " + empID + " was updated successfully";
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {

        Employee employee = findEmployee(empID);
        Manager updateManagerDegree;
        if (employee == null) {
            throw new Exception("Employee " + empID + " was not registered yet.");
        } else if (!newDegree.equals("PhD") && !newDegree.equals("MSc") && !newDegree.equals("BSc")) {
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        } else {
        updateManagerDegree = (Manager) employee; //Castar till Manager.
        updateManagerDegree.setDegree(newDegree);
        }
        return "Employee " + empID + " was updated successfully";
         }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {

        Employee employee = findEmployee(empID);
        Director updateDirectorDepartment;
        if (employee == null) {
            throw new Exception("Employee " + empID + " was not registered yet.");
        } else if (!newDepartment.equals("Business") && !newDepartment.equals("Human Resources") && !newDepartment.equals("Technical")) {
                    throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
                } else {
            updateDirectorDepartment = (Director) employee;
            updateDirectorDepartment.setDepartment(newDepartment);
        } return "Employee " + empID + " was updated successfully";
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        Employee employeeToUpdate = findEmployee(empID);
        if(employeeToUpdate == null) {
            throw new Exception("Employee " + empID + " was not registered yet.");
        }else if(newSalary == 0) {
            throw new Exception("Salary must be greater than zero.");
        } else {
         employeeToUpdate.setGrossSalary(newSalary);
        }
        return "Employee " + empID + " was updated successfully";
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        Map<String, Integer> mapDegrees = new HashMap<>();
        Manager manager;
        // Director extends Manager så det räcker med bara att räkna Manager.
        if(employeeList.isEmpty())
            throw new Exception("No employees registered yet.");
        for (Employee employee : employeeList) {
            if (employee instanceof Manager) {
                manager = (Manager) employee;

                try{ //Försöker lägga in ett värde på en degree om det degreet inte finns, catchar vi nullPointer och lägger till degreen samt sätter värdet till +1.
                    mapDegrees.put(manager.getDegree(), mapDegrees.get(manager.getDegree())+1);
                }catch (NullPointerException e){
                    mapDegrees.put(manager.getDegree(), 1);
                }
            }
        }
        return mapDegrees;
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        Employee employee = findEmployee(empID);
        if (employee == null) {
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        else {
            String name = employee.getName();
            double salary = employee.getRawSalary();
            Employee promotedManager = new Manager(empID, name, salary, degree);
            employeeList.remove(employee);
            employeeList.add(promotedManager);
        }
        return empID + " promoted successfully to Manager.";
    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        Employee employee = findEmployee(empID);
        if (employee == null) {
            throw new Exception("Employee " + empID + " was not registered yet.");
        } else {
            String name = employee.getName();
            double salary = employee.getRawSalary();
            Employee promotedDirector = new Director(empID, name, salary, degree, department);
            employeeList.remove(employee);
            employeeList.add(promotedDirector);
        } return empID + " promoted successfully to Director.";
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        Employee employee = findEmployee(empID);
        if (employee == null) {
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        else {
            String name = employee.getName();
            double salary = employee.getRawSalary();
            Employee promotedIntern = new Intern(empID, name, salary, gpa);
            employeeList.remove(employee);
            employeeList.add(promotedIntern);
        }
        return empID + " promoted successfully to Intern.";
    }

    public Employee findEmployee(String id) {
        try {
            if (employeeList.size() > 0) {
                for (Employee employee : employeeList) {
                    if (employee.getId().equals(id)) {
                        return employee;
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
