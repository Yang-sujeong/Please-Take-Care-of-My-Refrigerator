package spms.vo;

public class Review {
	
	protected int rev_num;
	protected String id;
	protected int shop_num;
	protected String text;
	protected String rating;
	
	
	public int getRev_num() {
		return rev_num;
		
	}
	public Review setRev_num(int rev_num) {
		this.rev_num = rev_num;
		return this;
	}
	public String getId() {
		return id;
	}
	public Review setId(String id) {
		this.id = id;
		return this;
	}
	public int getShop_num() {
		return shop_num;
	}
	public Review setShop_num(int shop_num) {
		this.shop_num = shop_num;
		return this;
	}
	public String getText() {
		return text;
	}
	public Review setText(String text) {
		this.text = text;
		return this;
	}
	public String getRating() {
		return rating;
	}
	public Review setRating(String rating) {
		this.rating = rating;
		return this;
	}
	
	
	
	
	
}
