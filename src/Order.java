

import java.util.Date;
public class Order {
	String buyer_username;
	String book_name;
	String voucher_name;
	float price;
	Date order_date;
	String type;
	int order_id;
	Order(String buyer_username,String type,String book_name,String voucher_name,Date order_date,float price){
		this.buyer_username = buyer_username;
		this.type = type;
		this.book_name = book_name;
		this.voucher_name = voucher_name;
		this.price = price;
		this.order_date = order_date;
	}
	Order(int order_id,String buyer_username,String type,String book_name,String voucher_name,Date order_date,float price){
		this.order_id = order_id;
		this.buyer_username = buyer_username;
		this.type = type;
		this.book_name = book_name;
		this.voucher_name = voucher_name;
		this.price = price;
		this.order_date = order_date;
	}
	public String toString() {
		return order_id + " " + book_name + " " + buyer_username + " "+voucher_name+ " "+" "+type + " " +order_date.toString() + " "+price;
	}
}
