

public class Buyer {
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private String phone_number;
	private String address;
	private String voucher = null;
	private int no_of_borrowed_books = 0;
	
	public Buyer(String firstname,String lastname,String username,String password,String email,String phone_number,String address){
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone_number = phone_number;
		this.address = address;	
	}
	public Buyer(String firstname,String lastname,String username,String password,String email,String phone_number,String address,int no_of_borrowed_books,String voucher){
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone_number = phone_number;
		this.address = address;
		this.no_of_borrowed_books = no_of_borrowed_books;
		this.voucher = voucher;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public int getNo_of_borrowed_books() {
		return no_of_borrowed_books;
	}

	public void setNo_of_borrowed_books(int no_of_borrowed_books) {
		this.no_of_borrowed_books = no_of_borrowed_books;
	}
	public String toString() {
		return firstname +"  " +lastname + " lives in " + address + " email : " + email + " phonenumber : "+phone_number;
	}
		
	
}
