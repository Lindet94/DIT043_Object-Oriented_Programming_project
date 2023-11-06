package Objects;

public abstract class Employee {
    private final String id;
    protected String name;
    protected double grossSalary;

    public Employee(String id, String name, double grossSalary) {
        this.id = id;
        this.name = name;
        this.grossSalary = grossSalary;
    }
    public double getNetSalary() {
        return 0.0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public String toString(){
        return "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public double getRawSalary(){
        return grossSalary;
    }
}


