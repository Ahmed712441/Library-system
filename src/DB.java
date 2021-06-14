

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialException;


public class DB {
	static Connection connection ;
	static Statement statement ;
	DB() throws SQLException{
		connection = DriverManager.getConnection("jdbc:sqlite:memory.db");
		statement = connection.createStatement();
	}
//	@Override
	public void update_adminstrator(String username, Adminstrator new_admin) throws SQLException {
		// TODO Auto-generated method stub
		if(!new_admin.getUsername().equals(username)) {
			if(check_Buyer_username(new_admin.getUsername())) {
			String query = String.format("UPDATE adminstrator set username = '%s',password='%s',first_name='%s',last_name='%s' where username = '%s';",
					new_admin.getUsername(),new_admin.getPassword(),new_admin.getFirst_name(),new_admin.getLast_name(),username);
			statement.executeUpdate(query);
			}
			else {
			System.out.println("cannot change  this ");
			}
			}
			else 
			{
				String query = String.format("UPDATE adminstrator set password='%s',first_name='%s',last_name='%s' where username = '%s' ;",
						new_admin.getPassword(),new_admin.getFirst_name(),new_admin.getLast_name(),username);
				statement.executeUpdate(query);
			}
	}
	public boolean check_admin_password(String username, String password) throws SQLException {
		String query = String.format("select * from adminstrator where username = '%s';",username);
		ResultSet result = statement.executeQuery(query);
		String pass;
		if(result.next()) {
			pass = result.getString("password");
			if(pass.equals(password)) {
				return true;
			}
			System.out.println("wrong password");
			return false;
		}else {
			System.out.println("there is no account with this username");
		}
		return false;
	}
	public void add_adminstrator(Adminstrator admin) throws SQLException, java.lang.Exception {
		// TODO Auto-generated method stub
		if(check_adminstrator_username(admin.getUsername())) {
		String query = String.format("INSERT INTO adminstrator  VALUES('%s','%s','%s','%s');",admin.getUsername(),admin.getPassword(),admin.getFirst_name(),admin.getLast_name());
		statement.executeUpdate(query);
		}else {
			throw new Exception("Already Used Username");
		}
	}
	public boolean check_adminstrator_username(String username) throws SQLException {
		String query = String.format("select * from adminstrator where username = '%s';",username);
		ResultSet result = statement.executeQuery(query);
		return !result.next();
	}
//	@Override
	public void update_buyer(String username, Buyer newbuyer) throws SQLException {
		// TODO Auto-generated method stub
		if(!newbuyer.getUsername().equals(username)) {
		if(check_Buyer_username(newbuyer.getUsername())) {
		String query = String.format("UPDATE Buyer Set username='%s', first_name = '%s' ,last_name = '%s',email='%s',phone='%s',address = '%s',no_OfBorrowedBooks=%d"
				+ ",voucher_code = '%s',password = '%s' WHERE username = '%s'",
				newbuyer.getUsername(),newbuyer.getFirstname(),newbuyer.getLastname(),newbuyer.getEmail(),newbuyer.getPhone_number(),newbuyer.getAddress(),newbuyer.getNo_of_borrowed_books(),newbuyer.getVoucher(),newbuyer.getPassword(),username);
		statement.executeUpdate(query);
		}
		else {
		System.out.println("cannot change  this ");
		}
		}
		else 
		{
			String query = String.format("UPDATE Buyer Set  first_name = '%s' ,last_name = '%s',email='%s',phone='%s',address = '%s',no_OfBorrowedBooks=%d"
					+ ",voucher_code = '%s',password = '%s' WHERE username = '%s'",
					newbuyer.getFirstname(),newbuyer.getLastname(),newbuyer.getEmail(),newbuyer.getPhone_number(),newbuyer.getAddress(),newbuyer.getNo_of_borrowed_books(),newbuyer.getVoucher(),newbuyer.getPassword(),username);
			statement.executeUpdate(query);

		}
	}

//	@Override
	public void change_password_buyer(String username, String newpassword) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("UPDATE Buyer Set password = '%s' WHERE username = '%s'",newpassword ,username);
		statement.executeUpdate(query);
	}

//	@Override
	public void change_password_adminstrator(String username, String newpassword) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("UPDATE adminstrator Set password = '%s' WHERE username = '%s'",newpassword ,username);
		statement.executeUpdate(query);
	}

//	@Override
	public void delete_buyer(String username) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE FROM Buyer where username = '%s'",username);
		statement.executeUpdate(query);
	}

//	@Override
	public void delete_adminstrator(String username) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE FROM adminstrator where username = '%s'",username);
		statement.executeUpdate(query);	
	}
	public void delete_all_admins() throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM adminstrator";
		statement.executeUpdate(query);	
	}
	public void delete_admins(String condition) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE FROM adminstrator where %s",condition);
		statement.executeUpdate(query);	
	}
	
//	@Override
	public Buyer get_buyer(String username) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("Select * FROM Buyer WHERE username = '%s'", username);
		ResultSet result = statement.executeQuery(query);
		String firstname;
		String lastname;
		String password;
		String email;
		String phone_number;
		String address;
		String voucher = null;
		int no_of_borrowed_books = 0;
		Buyer b = null;
		//Buyer(String firstname,String lastname,String username,String password,String email,String phone_number,String address,int no_of_borrowed_books,String voucher)
		while(result.next()) {
			firstname =  result.getString("first_name");
			lastname = result.getString("last_name");
			password = result.getString("password");
			email = result.getString("email");
			phone_number = result.getString("phone");
			address = result.getString("address");
			no_of_borrowed_books = result.getInt("no_OfBorrowedBooks");
			voucher = result.getString("voucher_code");
			b = new Buyer(firstname,lastname,username,password,email,phone_number,address,no_of_borrowed_books,voucher);
		}
		return b;
	}

//	@Override
	public void add_buyer(Buyer b) throws SQLException, BlacklistException, java.lang.Exception {
		// TODO Auto-generated method stub un,pass,fn,ln,email,phone,address
		if(!checkBlacklist(b.getEmail(),b.getPhone_number())) {
		if(check_Buyer_username(b.getUsername())) {
		String query = String.format("INSERT INTO buyer VALUES('%s','%s','%s','%s','%s','%s','%s',%d,null);",b.getUsername(),
				b.getPassword(),b.getFirstname(),b.getLastname(),b.getEmail(),b.getPhone_number(),b.getAddress(),b.getNo_of_borrowed_books());
		statement.executeUpdate(query);
		}else {
			throw new Exception("Username already used");
		}
		}else {
//			Blacklist B = getBlacklist(b.getEmail(),b.getPhone_number());
//			System.out.println(B.toString());
                        throw new BlacklistException("");
		}
	}
	public void add_buyers(Buyer[] b) throws SQLException {
		// TODO Auto-generated method stub un,pass,fn,ln,email,phone,address
		for(int i = 0;i<b.length;i++) {
		if(!checkBlacklist(b[i].getEmail(),b[i].getPhone_number())) {
		if(check_Buyer_username(b[i].getUsername())) {
		String query = String.format("INSERT INTO buyer VALUES('%s','%s','%s','%s','%s','%s','%s',%d,null);",b[i].getUsername(),
				b[i].getPassword(),b[i].getFirstname(),b[i].getLastname(),b[i].getEmail(),b[i].getPhone_number(),b[i].getAddress(),b[i].getNo_of_borrowed_books());
		statement.executeUpdate(query);
		}else {
			System.out.println(b[i].getUsername() + " is already used try to choose another username");
		}
		}else {
			Blacklist B = getBlacklist(b[i].getEmail(),b[i].getPhone_number());
			System.out.println(B.toString());
		}
	}
	}
	public boolean check_Buyer_username(String username) throws SQLException {
		String query = String.format("select * from Buyer where username = '%s'", username);
		ResultSet result = statement.executeQuery(query);
		return !result.next();
	}

//	@Override
	
	public void add_book(Book b) throws SQLException, java.lang.Exception {
		// TODO Auto-generated method stub
		if(check_book(b.getTitle(),b.type)) {
		String query = "Select max(book_id) FROM Book";
		ResultSet result = statement.executeQuery(query);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		query = String.format("INSERT INTO Book  VALUES(%d,'%s',%d,%.2f,'%s','%s');",length+1,b.getTitle(),b.getNo_of_books(),b.getPrice(),b.category,b.type);
		statement.executeUpdate(query);
		}else {
			throw new Exception("title is already used");
		}
	}
	public void add_books(Book[] b) throws SQLException {
		// TODO Auto-generated method stub
		String query = "Select max(book_id) FROM Book";
		ResultSet result = statement.executeQuery(query);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		int count = 1;
		for(int i = 0;i<b.length;i++) {
		if(check_book(b[i].getTitle(),b[i].type)) {
			query = String.format("INSERT INTO Book VALUES(%d,'%s',%d,%.2f,'%s','%s');",length+count,b[i].getTitle(),b[i].getNo_of_books(),b[i].getPrice(),b[i].category,b[i].type);
			statement.executeUpdate(query);
			count++;
		}else {
			System.out.println("Title is already used : "+b[i].getTitle());
		}
		}
	}
	public boolean check_book(String tittle,String type) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select * from Book where Title  = '%s' and type = '%s';", tittle,type);
		ResultSet result = statement.executeQuery(query);
		return !result.next();
	}
	public void delete_book(int bookid) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE from Book where book_id = %d ;",bookid);
		statement.executeUpdate(query);
	}
	public void delete_all_books() throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE from Book ;");
		statement.executeUpdate(query);
	}
	public void delete_books(String condition) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE from Book where %s;",condition);
		statement.executeUpdate(query);
	}
	public void delete_book(String Title) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE from Book where Title = '%s';",Title);
		statement.executeUpdate(query);
	}
	public Book[] get_books_by_category(String category) throws SQLException {
		String query = String.format("select count(*) from Book where category Like '%%%s' or category Like '%s%%' or category like '%%%s%%' ;",
				category,category,category);
		ResultSet all = statement.executeQuery(query);
		int length = 0;
		while(all.next()) {
				length = all.getInt(1);
		}
		query = String.format("select * from Book where category Like '%%%s' or category Like '%s%%' or category like '%%%s%%' ;",
				category,category,category);
		all = statement.executeQuery(query);Book b[] = new Book[length];
		String Title;
		int no_of_books;String type;
		float price;
		for (int i =0;i<b.length;i++) {
			all.next();
			Title = all.getString("Title");
			no_of_books = all.getInt("no_in_stock");
			price = all.getFloat("price");
			type = all.getString("type");
			b[i] = new Book(Title,no_of_books,price,category,type);
		}
		return b;
	}

//	@Override
	public Order[] get_all_orders() throws SQLException {
		// TODO Auto-generated method stub 
		ResultSet result = statement.executeQuery("select count(*) from orders");
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		Order[] orders = new Order[length];
		ResultSet all = statement.executeQuery("select * from orders");
		String buyer_username;
		String books_names;
		String voucher_name;
		float price;
		String order_date;
		String type;
		int order_id;
		for (int i = 0 ;i < length;i++) {
			all.next();
			order_id = all.getInt("order_id");
			buyer_username =  all.getString("buyer_username");
			books_names =  all.getString("books_names");
			voucher_name = all.getString("voucher_code");
			price = all.getFloat("price");
			order_date = all.getString("order_Date");
			type = all.getString("order_type");
			try {
				orders[i] = new Order(order_id,buyer_username,type,books_names,voucher_name,DateFor.parse(order_date),price);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orders;
	}
	public Order[] get_orders(String condition) throws SQLException {
		// TODO Auto-generated method stub 
		String query = String.format("select count(*) from orders where %s ;",condition);
		ResultSet all = statement.executeQuery(query);
		int length = 0;
		while(all.next()) {
			length = all.getInt(1);
		}
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		Order[] orders = new Order[length];
		query = String.format("select * from orders where %s ;",condition);
		all = statement.executeQuery(query);
		String buyer_username;
		String books_names;
		String voucher_name;
		float price;
		String order_date;
		String type;
		int order_id;
		for (int i = 0 ;i < length;i++) {
			all.next();
			order_id = all.getInt("order_id");
			buyer_username =  all.getString("buyer_username");
			books_names =  all.getString("books_names");
			voucher_name = all.getString("voucher_code");
			price = all.getFloat("price");
			order_date = all.getString("order_Date");
			type = all.getString("order_type");
			try {
				orders[i] = new Order(order_id,buyer_username,type,books_names,voucher_name,DateFor.parse(order_date),price);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orders;
	}

//	@Override
	public Order[] get_all_orders_for_a_book(String book_name) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select count(*) from orders where books_names Like '%%%s' or books_names Like '%s %%' or books_names like '%%%s%%' ;",book_name,
		book_name,book_name);
		ResultSet all = statement.executeQuery(query);
		int length = 0;
		while(all.next()) {
			length = all.getInt(1);
		}
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		Order[] orders = new Order[length];
		query = String.format("select * from orders where books_names Like '%%%s' or books_names Like '%s%%' or books_names like '%%%s%%' ;",book_name,
		book_name,book_name);
		all = statement.executeQuery(query);
		String buyer_username;
		String books_names;
		String voucher_name;
		float price;
		String order_date;
		String type;
		int order_id;
		for (int i = 0 ;i < length;i++) {
			all.next();
			order_id = all.getInt("order_id");
			buyer_username =  all.getString("buyer_username");
			books_names =  all.getString("books_names");
			voucher_name = all.getString("voucher_code");
			price = all.getFloat("price");
			order_date = all.getString("order_Date");
			type = all.getString("order_type");
			try {
				orders[i] = new Order(order_id,buyer_username,type,books_names,voucher_name,DateFor.parse(order_date),price);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orders;
	}

//	@Override
	public Order[] get_all_orders_from_a_buyer(String buyer_username) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select count(*) from orders where buyer_username = '%s' ;",buyer_username);
		ResultSet all = statement.executeQuery(query);
		int length = 0;
		while(all.next()) {
			length = all.getInt(1);
		}
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		Order[] orders = new Order[length];
		query = String.format("select * from orders where buyer_username = '%s' ;",buyer_username);
		all = statement.executeQuery(query);
		String books_names;
		String voucher_name;
		float price;
		String order_date;
		String type;
		int order_id;
		for (int i = 0 ;i < length;i++) {
			all.next();
			order_id = all.getInt("order_id");
			books_names =  all.getString("books_names");
			voucher_name = all.getString("voucher_code");
			price = all.getFloat("price");
			order_date = all.getString("order_Date");
			type = all.getString("order_type");
			try {
				orders[i] = new Order(order_id,buyer_username,type,books_names,voucher_name,DateFor.parse(order_date),price);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orders;
	}
	public float get_max_order_from_a_buyer(String buyer_username) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select max(price) from orders where buyer_username = '%s' ;",buyer_username);
		ResultSet all = statement.executeQuery(query);
		if(all.next()) {
			return all.getInt(1);
		}
		System.out.println("this buyer has no orders");
		return 0;
	}
	public float get_sum_of_orders() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select sum(price) from orders;";
		ResultSet all = statement.executeQuery(query);
		if(all.next()) {
			return all.getInt(1);
		}
		System.out.println("there is no orders yet");
		return 0;
	}
	public int get_count_of_orders() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select count(price) from orders;";
		ResultSet all = statement.executeQuery(query);
		if(all.next()) {
			return all.getInt(1);
		}
		System.out.println("there is no orders yet");
		return 0;
	}
	public void delete_all_order() throws SQLException {
		// TODO Auto-generated method stub
		String query = "Delete from orders;";
		statement.executeUpdate(query);
	}
	public float get_min_orders_from_a_buyer(String buyer_username) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select min(price) from orders where buyer_username = '%s' ;",buyer_username);
		ResultSet all = statement.executeQuery(query);
		if(all.next()) {
			return all.getInt(1);
		}
		System.out.println("this buyer has no orders");
		return 0;
	}
        public boolean check_Buyer(String username,String password) throws SQLException {
                  String query = String.format("select * from Buyer where username = '%s' and password ='%s'", username,password);
                   ResultSet result = statement.executeQuery(query);
                    return result.next();
        }
	public int get_count_orders_from_a_buyer(String buyer_username) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select count(*) from orders where buyer_username = '%s' ;",buyer_username);
		ResultSet all = statement.executeQuery(query);
		if(all.next()) {
			return all.getInt(1);
		}
		System.out.println("this buyer has no orders");
		return 0;
	}
	public int get_count_orders_for_a_book(String book_name) throws SQLException {
		// TODO Auto-generated method stub
//		select * from orders where books_names Like '%%%s' or books_names Like '%s%%' or books_names like '%%%s%%' ;
		String query = String.format("select count(*) from orders where books_names Like '%%%s' or books_names Like '%s%%' or books_names like '%%%s%%' ;",book_name,
		book_name,book_name);
		ResultSet all = statement.executeQuery(query);
		int length = 0;
		while(all.next()) {
			return all.getInt(1);
		}
		System.out.println("this book has no orders");
		return 0;
	}

//	@Override
	public float get_book_price(String book_name) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select price from Book where Title = '%s' ;",book_name);
		ResultSet result = statement.executeQuery(query);
		if(result.next()) {
			return result.getFloat("price");
		}
		System.out.println("there is no book with this title");
		return 0;
	}
	public Book get_book(String book_name,String type) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select * from Book where Title = '%s' and type = '%s';",book_name,type);
		ResultSet result = statement.executeQuery(query);
		int no_of_books;
		float price;String category;
		if(result.next()) {
			price = result.getFloat("price");
			no_of_books = result.getInt("no_in_stock");
			category = result.getString("Category");
			return (new Book(book_name,no_of_books,price,category,type));
		}
		System.out.println("there is no book with this title");
		return null;
	}
	public Book[] get_all_books() throws SQLException {
		// TODO Auto-generated method stub
		String query = "Select count(*) FROM Book";
		ResultSet result = statement.executeQuery(query);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		query = "select * from Book ;";
		result = statement.executeQuery(query);
		int no_of_books;String book_name;Book[] b = new Book[length];
		float price;int i = 0;String category;String type;
		while(result.next()) {
			book_name = result.getString("Title");
			price = result.getFloat("price");
			no_of_books = result.getInt("no_in_stock");
			category = result.getString("Category");type =result.getString("type");
			b[i]=new Book(book_name,no_of_books,price,category,type);
			i++;
		}
		return b;
	}
	public Book[] get_books(String condition) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("Select count(*) FROM Book where %s ;",condition);
		ResultSet result = statement.executeQuery(query);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		query = String.format("Select * FROM Book where %s ;",condition);
		result = statement.executeQuery(query);String category;String type;
		int no_of_books;String book_name;Book[] b = new Book[length];
		float price;int i = 0;
		while(result.next()) {
			book_name = result.getString("Title");
			price = result.getFloat("price");
			no_of_books = result.getInt("no_in_stock");
			category = result.getString("Category");type = result.getString("type");
			b[i]=new Book(book_name,no_of_books,price,category,type);
			i++;
		}
		return b;
	}
	public int get_book_id(String name,String type) throws SQLException {
		String query = String.format("Select book_id FROM Book where title = '%s' and type= '%s' ;",name,type);
		ResultSet result = statement.executeQuery(query);
		if(result.next()) {
		      return result.getInt(1);
		}
		return 0;
	}
        public int get_book_count(String name) throws SQLException {
		String query = String.format("Select no_in_stock FROM Book where title = '%s' ;",name);
		ResultSet result = statement.executeQuery(query);
		if(result.next()) {
		      return result.getInt(1);
		}
		return 0;
	}
//	@Override
	public void add_order(Order o) throws SQLException {
		// TODO Auto-generated method stub
		String query = "Select max(order_id) FROM orders";
		ResultSet result = statement.executeQuery(query);
		int num = 0;
		if(result.next()) {
			num = result.getInt(1);
		}
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(o.order_date);
		query = String.format("INSERT INTO orders VALUES(%d,'%s','%s','%s','%s','%s',%.2f);",num+1,
				o.buyer_username,o.book_name,o.voucher_name,o.type,date,o.price);
		statement.executeUpdate(query);
	}
	public void delete_orders(String condition) throws SQLException {
		// TODO Auto-generated method stub	
		String query = String.format("delete from orders where %s ",condition);
		statement.executeUpdate(query);
	}

//	@Override
	public void change_no_of_books(String book_name, String type ,int increment_by) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select no_in_stock from Book WHERE title = '%s' and type ='%s';",book_name,type);
		ResultSet result = statement.executeQuery(query);
		int no_of_books;
		if(result.next()) {
			no_of_books = result.getInt("no_in_stock");
			query = String.format("UPDATE Book Set no_in_stock = %d WHERE title = '%s' and type ='%s';",no_of_books+increment_by,book_name,type);
			statement.executeUpdate(query);
		}else {
			System.out.println("no book with this specifications");
		}
	}

//	@Override
	public void increment_no_of_books(String book_name,String type) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select no_in_stock from Book WHERE title = '%s' and type = '%s';",book_name,type);
		ResultSet result = statement.executeQuery(query);
		int no_of_books;
		if(result.next()) {
			no_of_books = result.getInt("no_in_stock");
			query = String.format("UPDATE Book Set no_in_stock = %d WHERE title = '%s' and type ='%s';",no_of_books+1,book_name,type);
			statement.executeUpdate(query);
		}else {
			System.out.println("no book with this specifications");
		}
	}
        public void increment_no_of_books(String book_name,String type,int increment_by) throws SQLException, java.lang.Exception {
		// TODO Auto-generated method stub
		String query = String.format("select no_in_stock from Book WHERE title = '%s' and type = '%s';",book_name,type);
		ResultSet result = statement.executeQuery(query);
		int no_of_books;
		if(result.next()) {
			no_of_books = result.getInt("no_in_stock");
			query = String.format("UPDATE Book Set no_in_stock = %d WHERE title = '%s' and type ='%s';",no_of_books+increment_by,book_name,type);
			statement.executeUpdate(query);
		}else {
			throw new Exception("no book with this specifications");
		}
	}

//	@Override
	public void update_book(String bookname, Book new_book) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("UPDATE Book Set no_in_stock = %d,price=%d WHERE title = '%s';",
				new_book.getNo_of_books(),new_book.getPrice(),bookname);
		statement.executeUpdate(query);
	}
	public void update_book(int bookid, Book new_book) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("UPDATE Book SET title = '%s',no_in_stock = %d,price=%d WHERE book_id = %d;",new_book.getTitle(),
				new_book.getNo_of_books(),new_book.getPrice(),bookid);
		statement.executeUpdate(query);
	}
public void close() {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
}
//	@Override
	
        public void add_voucher(Voucher voucher) throws SQLException, java.lang.Exception {
//		 TODO Auto-generated method stub  // yyyy-mm-dd
		if(check_voucher_code(voucher.getCode())) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(voucher.getExpiry_date());
		String query = String.format("INSERT INTO voucher  VALUES('%s',%d,'%s');", voucher.getCode(),voucher.getPercentage(),date);
		statement.executeUpdate(query);
		}else {
			throw new Exception("The oucher ode s lready n he able");
		}
	}

//	@Override
	public boolean check_voucher(String code , String buyer_username) throws SQLException {
// TODO Auto-generated method stub
                String query = String.format("select * from buyer where voucher_code = '%s' and username ='%s'; ", code,buyer_username);
                ResultSet result = statement.executeQuery(query);
                return result.next();
}
//	@Override
	public boolean check_voucher_code(String code) throws SQLException {
		String query = String.format("select * from voucher where code = '%s'", code);
		ResultSet result = statement.executeQuery(query);
		return !result.next();
	}
//	@Override
	public void delete_voucher(String code) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE FROM voucher WHERE code = '%s'", code);
		statement.executeUpdate(query);
	}
//	@Override
	public Voucher[] get_all_vouchers() throws SQLException {
		// TODO Auto-generated method stub
		ResultSet result = statement.executeQuery("select count(*) from voucher");
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		Voucher[] vouchers = new Voucher[length];
		ResultSet all = statement.executeQuery("select * from voucher");
		String code;String Date;
		int percentage;
		for (int i = 0 ;i < length;i++) {
			all.next();
			code =  all.getString("code");
			percentage =  all.getInt("percentage");
			Date = all.getString("expiry_Date");
			try {
				vouchers[i] = new Voucher(code,percentage,DateFor.parse(Date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vouchers;
	}
//	@Override
	public void delete_all_expired() throws SQLException {
		// TODO Auto-generated method stub
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		String query = String.format("DELETE FROM voucher WHERE expiry_Date < '%s'", date);
		statement.executeUpdate(query);
	}
//	@Override
	public Voucher getvoucher(String code) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("Select * FROM voucher WHERE code = '%s'", code);
		ResultSet result = statement.executeQuery(query);
		String Date;SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		int percentage;Voucher v;
		while(result.next()) {
			percentage =  result.getInt("percentage");
			Date = result.getString("expiry_Date");
			try {
				v = new Voucher(code,percentage,DateFor.parse(Date));
				return v;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
//	@Override
	public void delete_all_vouchers() throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM voucher";
		statement.executeUpdate(query);
	}
//	@Override
	public void add_voucher_toBuyer(String code, String username) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("UPDATE Buyer SET voucher_code = '%s' WHERE username = '%s';",code,username);
		statement.executeUpdate(query);
	}
//	@Override
	public Buyer[] get_all_buyers() throws SQLException {
		// TODO Auto-generated method stub
		ResultSet result1 = statement.executeQuery("select count(*) from Buyer");
		int length = 0;
		while(result1.next()) {
			length = result1.getInt(1);
		}
		Buyer[] b = new Buyer[length];
		String query = "Select * FROM Buyer ";
		ResultSet result = statement.executeQuery(query);
		String username;
		String firstname;
		String lastname;
		String password;
		String email;
		String phone_number;
		String address;
		String voucher = null;
		int no_of_borrowed_books = 0;
		//Buyer(String firstname,String lastname,String username,String password,String email,String phone_number,String address,int no_of_borrowed_books,String voucher)
		for (int i =0;i<length;i++) {
			result.next();
			username =result.getString("username");
			firstname =  result.getString("first_name");
			lastname = result.getString("last_name");
			password = result.getString("password");
			email = result.getString("email");
			phone_number = result.getString("phone");
			address = result.getString("address");
			no_of_borrowed_books = result.getInt("no_OfBorrowedBooks");
			voucher = result.getString("voucher_code");
			b[i] = new Buyer(firstname,lastname,username,password,email,phone_number,address,no_of_borrowed_books,voucher);
		}
		return b;
	}
//	@Override
	public Buyer[] get_buyers(String condition) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("select count(*) from Buyer where %s",condition);
		ResultSet result1 = statement.executeQuery(query);
		int length = 0;
		while(result1.next()) {
			length = result1.getInt(1);
		}
		Buyer[] b = new Buyer[length];
		query = String.format("select * from Buyer where %s",condition);
		ResultSet result = statement.executeQuery(query);
		String username;
		String firstname;
		String lastname;
		String password;
		String email;
		String phone_number;
		String address;
		String voucher = null;
		int no_of_borrowed_books = 0;
		//Buyer(String firstname,String lastname,String username,String password,String email,String phone_number,String address,int no_of_borrowed_books,String voucher)
		for (int i =0;i<length;i++) {
			result.next();
			username =result.getString("username");
			firstname =  result.getString("first_name");
			lastname = result.getString("last_name");
			password = result.getString("password");
			email = result.getString("email");
			phone_number = result.getString("phone");
			address = result.getString("address");
			no_of_borrowed_books = result.getInt("no_OfBorrowedBooks");
			voucher = result.getString("voucher_code");
			b[i] = new Buyer(firstname,lastname,username,password,email,phone_number,address,no_of_borrowed_books,voucher);
		}
		return b;
		
	}
	public void delete_all_Buyers() throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE FROM Buyer");
		statement.executeUpdate(query);
	}
	public void delete_Buyers(String condition) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("DELETE FROM Buyer where %s",condition);
		statement.executeUpdate(query);
	}
	public Adminstrator get_admin(String username) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("Select * FROM adminstrator WHERE username = '%s'", username);
		ResultSet result = statement.executeQuery(query);
		String firstname;
		String lastname;
		String password;
		Adminstrator b = null;
		//Buyer(String firstname,String lastname,String username,String password,String email,String phone_number,String address,int no_of_borrowed_books,String voucher)
		while(result.next()) {
			firstname =  result.getString("first_name");
			lastname = result.getString("last_name");
			password = result.getString("password");
			b = new Adminstrator(username,password,firstname,lastname);
		}
		return b;
	}
	public Adminstrator[] get_all_admin() throws SQLException {
		// TODO Auto-generated method stub
		String query = "Select count(*) FROM adminstrator";
		ResultSet result = statement.executeQuery(query);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		query = "Select * FROM adminstrator";
		result = statement.executeQuery(query);
		String firstname;
		String lastname;
		String password;
		String username;
		Adminstrator[] b = new Adminstrator[length];
		int i = 0;
		//Buyer(String firstname,String lastname,String username,String password,String email,String phone_number,String address,int no_of_borrowed_books,String voucher)
		while(result.next()) {
			username = result.getString("username");
			firstname =  result.getString("first_name");
			lastname = result.getString("last_name");
			password = result.getString("password");
			b[i] = new Adminstrator(username,password,firstname,lastname);
			i++;
		}
		return b;
	}
	public Adminstrator[] get_admins(String condition) throws SQLException {
		// TODO Auto-generated method stub
		String query = String.format("Select count(*) FROM adminstrator where %s",condition);
		ResultSet result = statement.executeQuery(query);
		int length = 0;
		while(result.next()) {
			length = result.getInt(1);
		}
		query = String.format("Select * FROM adminstrator where %s",condition);
		result = statement.executeQuery(query);
		String firstname;
		String lastname;
		String password;
		String username;
		Adminstrator[] b = new Adminstrator[length];
		int i = 0;
		//Buyer(String firstname,String lastname,String username,String password,String email,String phone_number,String address,int no_of_borrowed_books,String voucher)
		while(result.next()) {
			username = result.getString("username");
			firstname =  result.getString("first_name");
			lastname = result.getString("last_name");
			password = result.getString("password");
			b[i] = new Adminstrator(username,password,firstname,lastname);
			i++;
		}
		return b;
	}
	public boolean verify_buyer_account(String username,String password) throws SQLException {
		String query = String.format("Select password FROM Buyer where username = '%s'",username);
		ResultSet result = statement.executeQuery(query);
		String pass;
		if(result.next()) {
			pass = result.getString("password");
			if(pass.equals(password)) {
				return true;
			}
			System.out.println("this password is incorrect");
		}
		System.out.println("this username is incorrect");
		return false;
	}
	public void addToBlacklist(Blacklist b) throws SQLException {
		String query = "Select max(blacklist_id) FROM Blacklist";
		ResultSet result = statement.executeQuery(query);
		int num = 0;
		if(result.next()) {
			num = result.getInt(1);
		}
		query = String.format("INSERT INTO Blacklist VALUES(%d,'%s','%s','%s','%s','%s','%s');"
				,num+1,b.phone_num,b.email,b.address,b.first_name,b.last_name,b.reason);
		statement.executeUpdate(query);
	}
	public boolean checkBlacklist(String email,String phonenumber) throws SQLException {
		String query = String.format("Select * from blacklist where email = '%s' or phonenumber = '%s' ; ",email,phonenumber);
		ResultSet result = statement.executeQuery(query);
		return result.next();
	}
	public void removefromBlacklist(String email,String phonenumber) throws SQLException {
		String query = String.format("Delete from blacklist where email = '%s' or phonenumber = '%s' ; ",
				email,phonenumber);
		statement.executeUpdate(query);
	}
	public void removeAllfromBlacklist() throws SQLException {
		String query = String.format("Delete from blacklist ; ");
		statement.executeUpdate(query);
	}
	public Blacklist getBlacklist(String email,String phonenumber) throws SQLException {
		String query = String.format("select * from blacklist where email = '%s' or phonenumber = '%s' ; ",
				email,phonenumber);
		ResultSet result = statement.executeQuery(query);
		Blacklist b =null;String first_name;String last_name;String reason;String address;
		if(result.next()) {
			first_name =result.getString("fisrt_name");
			last_name=result.getString("last_name");reason=result.getString("reason");
			address =result.getString("address");
			b = new Blacklist(email,phonenumber,first_name,last_name,reason,address); 
		}
		return b;
	}
	public void addToRentlist(Rented_books r) throws SQLException {
		String query = "Select max(rent_id) FROM rentend_Books";
		ResultSet result = statement.executeQuery(query);
		int num = 0;
		if(result.next()) {
			num = result.getInt(1);
		}
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(r.retrieval_date);
		query = String.format("INSERT INTO rentend_Books VALUES(%d,'%s','%s','%s');"
				,num+1,r.buyer_username,r.book_title,date);
		statement.executeUpdate(query);
	}
	public void removeFromRentlist(String username,String book) throws SQLException {
		String query = String.format("delete from rentend_Books where buyer_username = '%s' and Book_title = '%s' ;"
				,username,book);
		statement.executeUpdate(query);
	}
	
	public void removeFromRentlist(String condition) throws SQLException {
		String query = String.format("delete from rentend_Books where %s ;"
				,condition);
		statement.executeUpdate(query);
	}
	public void removeAllFromRentlist() throws SQLException {
		String query = "delete from rentend_Books ;";
		statement.executeUpdate(query);
	}
	public Rented_books[] get_rents(String condition) throws SQLException {
		String query = String.format("Select count(rent_id) FROM rentend_Books where %s",condition);
		ResultSet result = statement.executeQuery(query);
		int num = 0;
		if(result.next()) {
			num = result.getInt(1);
		}
		Rented_books[] r = new Rented_books[num];String buyer_username;String book; 
		String Date;SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		query = String.format("Select * FROM rentend_Books where %s",condition);
		result = statement.executeQuery(query);
		for (int i = 0 ;i < num;i++) {
			result.next();
			buyer_username =  result.getString("buyer_username");
			Date =  result.getString("retrival_date");
			book = result.getString("Book_title");
			try {
				r[i] = new Rented_books(book,DateFor.parse(Date),buyer_username);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return r;		
	}
	public Rented_books[] get_all_rents() throws SQLException {
		String query = "Select count(rent_id) FROM rentend_Books";
		ResultSet result = statement.executeQuery(query);
		int num = 0;
		if(result.next()) {
			num = result.getInt(1);
		}
		Rented_books[] r = new Rented_books[num];String buyer_username;String book; 
		String Date;SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		query = "Select * FROM rentend_Books ";
		result = statement.executeQuery(query);
		for (int i = 0 ;i < num;i++) {
			result.next();
			buyer_username =  result.getString("buyer_username");
			Date =  result.getString("retrival_date");
			book = result.getString("Book_title");
			try {
				r[i] = new Rented_books(book,DateFor.parse(Date),buyer_username);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return r;	
	}
        public ResultSet getallrents() throws SQLException {
		String query = "Select * FROM rentend_Books ;";
		ResultSet result = statement.executeQuery(query);
                return result;
		
	}
	public void insertimage(String imgpath,String book_title) throws IOException, SerialException, SQLException {
		byte[] data = convertimagetobyte(imgpath);
		String query = String.format("INSERT INTO images VALUES('%s',null);",book_title);
		statement.executeUpdate(query);
		String updateSQL = "UPDATE images "
                + "SET image = ? "
                + "WHERE book_Title = ?";
		PreparedStatement pstmt = connection.prepareStatement(updateSQL) ;
        pstmt.setBytes(1, data);
        pstmt.setString(2, book_title);

        pstmt.executeUpdate();
	}
	public BufferedImage getimage(String book_title) throws SQLException, IOException {
		String query = String.format("select image from images where book_Title ='%s';",book_title);
		ResultSet result = statement.executeQuery(query);Blob image;
		if(result.next()) {
			InputStream in = result.getBinaryStream("image");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead = in.read(buffer);
			while (bytesRead > -1)
			{
			  out.write(buffer,0, bytesRead);
			  bytesRead = in.read(buffer);
			}
			byte[] picture = out.toByteArray();
			in.close();
//			image = result.getBlob("image");
//			byte[] blobAsBytes = image.getBytes(1, (int)image.length());
			return convertbytetoimage(picture);
		}
		System.out.println("no book with this title have images");
		return null;
	}
	public void deleteimage(String book_title) throws SQLException {
		String query = String.format("delete from images where book_Title ='%s';",book_title);
		statement.executeUpdate(query);
	}
	public void deleteAllimages() throws SQLException {
		String query = "delete from images;";
		statement.executeUpdate(query);
	}
	public static byte[] convertimagetobyte(String path) throws IOException {
		BufferedImage bImage = ImageIO.read(new File(path));
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    String type = path.split("\\.")[1];
	    ImageIO.write(bImage, type, bos );
	    byte [] data = bos.toByteArray();
	    return data;
	}
	public static BufferedImage convertbytetoimage(byte [] data) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
	    BufferedImage bImage2 = ImageIO.read(bis);
	    return bImage2;
	}
	public void cleardatabase() throws SQLException {
		delete_all_vouchers();
		deleteAllimages();
		removeAllfromBlacklist();
		delete_all_admins();
		delete_all_Buyers();
		delete_all_books();
		delete_all_order();
		removeAllFromRentlist();	
	}

    private Exception Exception(String already_Used_Username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class BlacklistException extends Exception {

        public BlacklistException(String string) {
            super("This person in blacklist");
        }
    }
	
}
