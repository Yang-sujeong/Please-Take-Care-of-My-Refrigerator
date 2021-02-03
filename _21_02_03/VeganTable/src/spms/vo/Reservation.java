package spms.vo;

public class Reservation {
	protected int rsv_num;
	protected String id;
	protected int shop_num;
	protected String shop_name;
	protected String rsv_date;
	protected int rsv_totalnum;
	
	public int getRsv_num() {
		return rsv_num;
	}
	public Reservation setRsv_num(int rsv_num) {
		this.rsv_num = rsv_num;
		return this;
	}
	public String getId() {
		return id;
	}
	public Reservation setId(String id) {
		this.id = id;
		return this;
	}
	public int getShop_num() {
		return shop_num;
	}
	public Reservation setShop_num(int shop_num) {
		this.shop_num = shop_num;
		return this;
	}
	public String getShop_name() {
		return shop_name;
	}
	public Reservation setShop_name(String shop_name) {
		this.shop_name = shop_name;
		return this;
	}
	public String getRsv_date() {
		return rsv_date;
	}
	public Reservation setRsv_date(String rsv_date) {
		this.rsv_date = rsv_date;
		return this;
	}
	public int getRsv_totalnum() {
		return rsv_totalnum;
	}
	public Reservation setRsv_totalnum(int rsv_totalnum) {
		this.rsv_totalnum = rsv_totalnum;
		return this;
	}
	
	
	
	
}









