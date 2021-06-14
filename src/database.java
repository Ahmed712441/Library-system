

import java.sql.SQLException;

public interface database {
	

 	
	public abstract void update_adminstrator(String username,Adminstrator new_admin) throws SQLException;
	public abstract void update_buyer(String username,Buyer newbuyer) throws SQLException;
	public abstract void change_password_buyer(String username,String newpassword)throws SQLException ;
	public abstract void change_password_adminstrator(String username,String newpassword)throws SQLException ;
	public abstract void delete_buyer(String username) throws SQLException;
	public abstract Buyer get_buyer(String username) throws SQLException;
	public abstract Buyer[] get_all_buyers() throws SQLException;
	public abstract Buyer[] get_buyers(String condition) throws SQLException;
	public abstract void delete_adminstrator(String username) throws SQLException;
	public abstract void add_buyer(Buyer b) throws SQLException;
	public abstract void add_voucher_toBuyer(String code,String username) throws SQLException;
	public abstract void add_book(Book b) throws SQLException ;
	public abstract Order[] get_all_orders() throws SQLException;
	public abstract Order[] get_all_orders_for_a_book(String book_name) throws SQLException;
	public abstract Order[] get_all_orders_from_a_buyer(String book_name) throws SQLException;
	public abstract float get_book_price(String book_name) throws SQLException;
	public abstract void add_order(Order o) throws SQLException;
	public abstract void increment_no_of_books(String book_name,int increment_by) throws SQLException;
	public abstract void increment_no_of_books(String book_name) throws SQLException;
	public abstract void update_book(String book_name,Book new_book) throws SQLException ;
	public abstract void add_voucher(Voucher voucher) throws SQLException;
	public abstract boolean check_voucher(String code,String buyer_username) throws SQLException;
	public abstract boolean check_voucher_code(String code) throws SQLException;
	public abstract void delete_voucher(String code) throws SQLException;
	public abstract Voucher[] get_all_vouchers() throws SQLException;
	public abstract void delete_all_expired() throws SQLException;
	public abstract Voucher getvoucher(String code) throws SQLException;
	public abstract void delete_all_vouchers() throws SQLException;
}
