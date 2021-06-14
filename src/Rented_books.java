

import java.util.Date;

public class Rented_books {
	String book_title;
	Date retrieval_date;
	String buyer_username;
	Rented_books(String book_title,Date retrieval_date,String buyer_username){
		this.book_title = book_title;
		this.retrieval_date = retrieval_date;
		this.buyer_username = buyer_username;
	}
	public String toString() {
		return buyer_username+"borrowed"+book_title+" to" +retrieval_date;
	}
}
