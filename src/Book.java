

public class Book {

	private String Title;
	private int no_of_books;
	private float price;
	String category;
	String type;
	public Book(String name,int no_of_books,float price,String category,String type) {
		this.Title = name;
		this.no_of_books = no_of_books;
		this.price = price;
		this.category = category;
		this.type = type;
	}

	public String getTitle() {
		return Title;
	}

	public void setName(String name) {
		this.Title = name;
	}

	public int getNo_of_books() {
		return no_of_books;
	}

	public void setNo_of_books(int no_of_books) {
		this.no_of_books = no_of_books;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	public String toString() {
		return ("we have in stock "+this.no_of_books+" of "+this.Title+" and its price is "+this.price +" for "+ this.type);
	}
}
