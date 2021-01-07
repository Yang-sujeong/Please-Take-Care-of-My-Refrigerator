package airline;

public class CurAccount {
	private String accNum;
	private String id;
	private String name;
	
	public CurAccount(String accNum, String id, String name) {
		this.accNum = accNum;
		this.id = id;
		this.name = name;
	}

	public String getAccNum() {
		return accNum;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id=id;
	}
}
