package Objects;

public class Review {
   protected String id;
   public int grade;
   public String comment;

   public Review(int grade, String comment, String id) {
      this.grade = grade;
      this.comment = comment;
      this.id = id;
   }

   public Review(int grade, String id){
      this.grade = grade;
      this.id = id;
   }

   public int getGrade(int grade) {
      this.grade = grade;
      return this.grade;
   }

   public void getComment(String comment) {
      this.comment = comment;
   }

   public String toString() {
      String reviewString =  "Grade: "+grade +"."+comment;
      return reviewString;
   }

}