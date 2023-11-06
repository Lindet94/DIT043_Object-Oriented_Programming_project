package Menus;
import Utility.Prints;
import Utility.UserInput;
import facade.Facade;
import java.util.Scanner;

    public class EmployeeMenu {
    public static Facade facade = new Facade(); // För att använda samma facade hela tiden

    public void employeeMenu() {

        Scanner input = new Scanner(System.in);
        String menuOption;
        do {
            Prints.employeeMenu();
            menuOption = input.nextLine();
            switch (menuOption) {
                case "0":
                    MainMenu.mainMenu();
                    break;
                case "1": {
                    String id = UserInput.readLine("Input ID:");
                    String name = UserInput.readLine("Input name:");
                    double salary = UserInput.readDouble("Input salary:");
                    try {
                        String message = facade.createEmployee(id, name, salary);
                        System.out.println(message);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        exception.printStackTrace();
                    }
                }
                    break;
                case "2": {
                    String id = UserInput.readLine("Input ID:");
                    String name = UserInput.readLine("Input name:");
                    double salary = UserInput.readDouble("Input salary:");
                    String degree = UserInput.readLine("Input degree: (BSc/MSc/PhD)");
                    try {
                        String message3 = facade.createEmployee(id, name, salary, degree);
                        System.out.println(message3);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        exception.printStackTrace();
                    }
                }
                    break;
                case "3": {
                    String id = UserInput.readLine("Input ID:");
                    String name = UserInput.readLine("Input name:");
                    double salary = UserInput.readDouble("Input salary:");
                    String degree = UserInput.readLine("Input degree: (Bsc/MSc/PhD)");
                    String department = UserInput.readLine("Input dept: (HR/Technical/Business)");
                    try {
                        String message = facade.createEmployee(id, name, salary, degree, department);
                        System.out.println(message);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        exception.printStackTrace();
                    }
                }
                    break;
                case "4": {
                    String id = UserInput.readLine("Input ID:");
                    String name = UserInput.readLine("Input name:");
                    double salary = UserInput.readDouble("Input salary:");
                    int gpa = UserInput.readInt("Please input GPA: (1-10");
                    try {
                        String message = facade.createEmployee(id, name, salary, gpa);
                        System.out.println(message);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                    break;
                case "5": {
                    String id = UserInput.readLine("Input ID of employee you want to remove");
                    try {
                        String result = facade.removeEmployee(id);
                        System.out.println(result);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        exception.printStackTrace();
                    }
                }
                    break;
                case "6": {
                    String id = UserInput.readLine("Input ID:");
                    try {
                        String message = facade.printEmployee(id);
                        System.out.println(message);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                    break;
                case "7": {
                    try {
                        String message = facade.printAllEmployees();
                        System.out.println(message);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                    break;
                case "8": {
                    try {
                        Double message = facade.getTotalNetSalary();
                        System.out.println(message);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        exception.printStackTrace();
                    }
                }
                    break;
                case "9": {
                    try {
                        String message = facade.printSortedEmployees();
                        System.out.println(message);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        exception.printStackTrace();
                    }
                }
                    break;
                default:
                    Prints.invalidOption();
            }
        } while (!(menuOption.equals("0")));
        UserInput.closeScanner();
    } 
}