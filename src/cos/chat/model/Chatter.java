package cos.chat.model;

public class Chatter {
	
	
	private int id;
	private String name;
	private int groupID;
	private int usedNumber;
	private String content;
	private String groupName;
	private String indentifier;
	public String getIndentifier() {
		return indentifier;
	}
	public void setIndentifier(String indentifier) {
		this.indentifier = indentifier;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String url;
	private String type;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Chatter [id=" + id + ", name=" + name + ", groupID=" + groupID
				+ ", usedNumber=" + usedNumber + ", content=" + content
				+ ", groupName=" + groupName + ", indentifier=" + indentifier
				+ ", url=" + url + ", type=" + type + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public int getUsedNumber() {
		return usedNumber;
	}
	public void setUsedNumber(int usedNumber) {
		this.usedNumber = usedNumber;
	}

}
