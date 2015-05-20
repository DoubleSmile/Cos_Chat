package cos.chat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cos.chat.model.Link;

@Repository
public class LinkDAO {
	
	@Autowired
	private JdbcTemplate template;
	public JdbcTemplate getTemplate() {
		return template;
	}
	
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}


	public boolean exists(Link link){
		if(link.getId()==0 || link.getCreaterID()==0 || link.getReceiverID()==0)
			return false;
		return true;
	}
	
	public void addLink(int createrID,int receiverID){
		final String sql="INSERT INTO link VALUES (null, ? , ? )";
		this.getTemplate().update(sql,new Object[] {createrID,receiverID});
	}
	
	public void dropLink(int chatterID){
		final String sql="DELETE FROM link WHERE createrID = ? or receiverID = ?";
		int modefiedCount=this.getTemplate().update(sql,new Object[] {chatterID,chatterID});
		if(modefiedCount==0)
			throw new NullPointerException("没有chatterID对应的信息");
	}
	
	public int getLinkedID(int chatterID){
		final Link link=new Link();
		this.getTemplate().query("SELECT * FROM link WHERE createrID = ? || receiverID = ?", new Object[] {chatterID,chatterID},
				new RowCallbackHandler(){
			public void processRow(ResultSet rs)throws SQLException{
				if(rs.wasNull()){
					return;
				}
				link.setId(new Integer(rs.getInt("id")));
				link.setCreaterID(new Integer(rs.getInt("createrID")));
				link.setReceiverID(new Integer(rs.getInt("receiverID")));
			}
		});
		if(!exists(link))
			return 0;
		else {
			if(link.getCreaterID()==chatterID)
				return link.getReceiverID();
			else
				return link.getCreaterID();
		}
			
	}




}
