package Objects;
import java.text.DecimalFormat;

public class Transaction {
    private String id;
    private final int unitsBought;
    private final double totalPricePaid;

    public Transaction(String id, int amountBought, double totalPricePaid) {
        this.id = id;
        this.unitsBought = amountBought;
        this.totalPricePaid = totalPricePaid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUnitsBought() {
        return unitsBought;
    }

    public double getTotalPricePaid() {
        return totalPricePaid;
    }

    public String toString() {
        DecimalFormat decimal2 = new DecimalFormat("###.00");
        String grossSalaryPrint = decimal2.format(this.totalPricePaid);
        String transactionString = id + ": " + unitsBought + " item(s). " + grossSalaryPrint + " SEK";
        return transactionString;
    }
}


