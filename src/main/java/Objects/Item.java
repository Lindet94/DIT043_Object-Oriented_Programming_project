package Objects;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class  Item {
    private String id;
    private String name;
    private double price;
    public ArrayList<Review> reviewList;

    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.reviewList = new ArrayList<>();
    }

    public void newReview(int grade, String comment) {
        String reviewID = getId();
        setId(reviewID);
        Review newReview = new Review(grade, comment, reviewID);
        addToReviews(newReview);
    }

    public void newReview2(int grade, String id) {
        String reviewID = getId();
        setId(reviewID);
        Review newReview2 = new Review(grade, id);
        addToReviews(newReview2);
    }

    public String toString() {
        DecimalFormat decimal2 = new DecimalFormat("###.00");
        String grossSalaryPrint = decimal2.format(this.price);
        return id + ": " + name + ". " + grossSalaryPrint + " SEK";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addToReviews(Review review) { //Lägga till review i aktuell ArrayList
        this.reviewList.add(review);
    }

    public int getNumberOfReviews() {
        return reviewList.size();
    }

}