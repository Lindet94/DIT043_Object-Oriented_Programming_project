package Objects;
import java.text.DecimalFormat;

public class RegularEmployee extends Employee {

    public RegularEmployee(String id, String name, double grossSalary) {
        super(id, name, grossSalary);
    }

@Override
    public double getNetSalary() {
        double netSalary = getGrossSalary() - (getGrossSalary() * 0.1);
        return netSalary;
   }

    @Override
    public String toString(){
        DecimalFormat decimal2 = new DecimalFormat("###.00");
        String grossSalaryPrint = decimal2.format(getGrossSalary());
        String regularEmployeeString = name+"'s" + " gross salary is " + grossSalaryPrint + " SEK per month.";
        return regularEmployeeString;
   }


}
