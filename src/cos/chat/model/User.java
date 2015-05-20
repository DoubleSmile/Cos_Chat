package cos.chat.model;

public class User {
	private int id;
	private int state;
	public User(String identifier, int chatterID) {
		super();
		this.identifier = identifier;
		this.chatterID = chatterID;
	}
	private String identifier;
	@Override
	public String toString() {
		return "User [id=" + id + ", state=" + state + ", identifier="
				+ identifier + ", chatterID=" + chatterID + "]";
	}
	private int chatterID;
	public int getChatterID() {
		return chatterID;
	}
	public User( String identifier,int chatterID, int state) {
		super();
		this.state = state;
		this.identifier = identifier;
		this.chatterID = chatterID;
	}
	public void setChatterID(int chatterID) {
		this.chatterID = chatterID;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	

}
