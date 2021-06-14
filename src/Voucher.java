

import java.sql.SQLException;
import java.util.Date;

public class Voucher  {
	private String code;
	private int percentage;
	private Date expiry_date;
	Voucher(String code,int percentage,Date expiry_date) {
		this.code = code;
		this.percentage = percentage;
		this.expiry_date = expiry_date;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String toString() {
		return code + "   " + percentage + "%  " + expiry_date.toString();
	}
}
