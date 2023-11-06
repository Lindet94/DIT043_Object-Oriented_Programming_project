package Objects;
import facade.Facade;

import java.text.DecimalFormat;

public class Manager extends Employee {

    protected String degree;

    public Manager(String id, String name, double grossSalary, String degree) {
        super(id, name, grossSalary);
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public double getNetSalary() {
        double netSalary = getGrossSalary() - (getGrossSalary() * 0.1);
        return netSalary;
    }

    @Override
    public double getGrossSalary() {
        double grossSalary = super.grossSalary;
        if (degree.equals("BSc")) {
            grossSalary = grossSalary * 1.1;
        } else if (degree.equals("MSc")) {
            grossSalary = grossSalary * 1.2;
        } else if (degree.equals("PhD")) {
           grossSalary = grossSalary * 1.35;
        }
        return Facade.truncateTo(grossSalary, 2);
    }

    @Override
    public String toString(){ // Overrides the "EmployeeToString"
        DecimalFormat decimal2 = new DecimalFormat("###.00");
        String grossSalaryPrint2 = decimal2.format(getGrossSalary());
        String managerString = degree + " " + name+"'s" + " gross salary is " + grossSalaryPrint2 + " SEK per month.";
        return managerString;
    }
}
