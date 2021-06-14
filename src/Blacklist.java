public class Blacklist {
	String phone_num;
	String email;
	String first_name;
	String last_name;
	String address;
	String reason;
	Blacklist(String phone_num,String email,String first_name,String last_name,String reason,String address){
		this.phone_num =phone_num;
		this.email=email;
		this.address = address;
		this.first_name=first_name;
		this.last_name=last_name;
		this.reason = reason;
	}
	public String toString() {
		return first_name + " " + last_name + " was blocked because " +reason;
	}
}
