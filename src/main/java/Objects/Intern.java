package Objects;

public class Intern extends Employee {
    private int gpa;

    public Intern(String id, String name, double grossSalary, int gpa) {
        super(id, name, grossSalary);
        this.gpa = gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    @Override
    public double getNetSalary() {
        double netSalary = getGrossSalary();
        return netSalary;
    }

    @Override
    public double getGrossSalary() {
        if (gpa <= 5) {
            return 0.0;
        } else if (gpa > 5 && gpa < 8) {
            return grossSalary;
        } else {
            return grossSalary + 1000;
        }
    }

    @Override
    public String toString(){ // Overrides the "EmployeeToString"
        String grossSalaryPrint = String.format("%.2f", this.getGrossSalary());
        String internString = name+"'s" + " gross salary is " + grossSalaryPrint + " SEK per month. GPA: " + gpa;
        return internString;
    }
}

