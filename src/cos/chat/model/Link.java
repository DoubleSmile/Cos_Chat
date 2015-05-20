package cos.chat.model;

public class Link {
	private int id;
	private int createrID;
	private int receiverID;
	@Override
	public String toString() {
		return "Link [id=" + id + ", createrID=" + createrID + ", receiverID="
				+ receiverID + "]";
	}
	public int getId() {
		return id;
	}
	public Link() {
		super();
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreaterID() {
		return createrID;
	}
	public void setCreaterID(int createrID) {
		this.createrID = createrID;
	}
	public int getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}
	public Link(int id, int createrID, int receiverID) {
		super();
		this.id = id;
		this.createrID = createrID;
		this.receiverID = receiverID;
	}

}
