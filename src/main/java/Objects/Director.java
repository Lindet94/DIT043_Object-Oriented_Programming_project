package Objects;
import facade.Facade;

import java.text.DecimalFormat;

public class Director extends Manager {

    private String department;

    public Director(String id, String name, double grossSalary, String degree, String department) {
        super(id, name, grossSalary, degree);
        this.department = department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        DecimalFormat decimal2 = new DecimalFormat("###.00");
        String grossSalaryPrint = decimal2.format(getGrossSalary());
        String directorString = degree + " " + name + "'s" + " gross salary is " + grossSalaryPrint + " SEK per month. Dept: " + department;
        return directorString;
    }

    @Override
    public double getGrossSalary() {

        double grossSalary = super.grossSalary;
        if (degree.equals("BSc")) {
            grossSalary = grossSalary * 1.1 + 5000;
        } else if (degree.equals("MSc")) {
            grossSalary = grossSalary * 1.2 + 5000;
        } else if(degree.equals("PhD")) {
            grossSalary = grossSalary * 1.35 + 5000;
        }
        return Facade.truncateTo(grossSalary, 2);

    }
    @Override
    public double getNetSalary() {
        double lowTax = getGrossSalary() - (getGrossSalary() * 0.1);
        double mediumTax = getGrossSalary() - (getGrossSalary() * 0.2);
        double highTax = getGrossSalary() - (0.4 * (getGrossSalary()-30000) + (0.2 * 30000));

        if (getGrossSalary() < 30000) {
            return lowTax;
        } else if (getGrossSalary() > 30000 && getGrossSalary() < 50000) {
            return mediumTax;
        } else
            return highTax;
        }
    }


