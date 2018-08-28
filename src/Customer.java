

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        String result = "Rental record for " + getName() + "\n";
        for(Rental each : rentals) {
            double thisAmount = 0;

            // TODO 1 extract method. thisAmount = amountFor(each)
            // 一行ごとに金額計算
            switch( each.getMovie().getPriceCode() ) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if( each.getDaysRented() > 2)
                        thisAmount += ( each.getDaysRented() -2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if ( each.getDaysRented() > 3 )
                        thisAmount += (each.getDaysRented() -3 ) * 1.5;
                    break;
            }

            // レンタルポイントを加算
            frequentRenterPoints ++;
            // 新作を二日以上借りた場合はボーナスポイント
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    each.getDaysRented() > 1) frequentRenterPoints ++;

            // この貸し出しに関する数値の表示
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
        return result;
    }
}
