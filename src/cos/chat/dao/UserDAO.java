package cos.chat.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cos.chat.model.User;


@Repository
public class UserDAO {
	
	@Autowired
	private JdbcTemplate template;
	public JdbcTemplate getTemplate() {
		return template;
	}
	
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void add(User user){
		this.getTemplate().update("insert into User values(?,?,?,?)",
				new Object[]{null,user.getChatterID(),user.getIdentifier(),user.getState()});
		System.out.println(user.toString());
	}
	
	public void setReady(int userID){
		this.getTemplate().update("update User set state = 1 where id = ?",
				new Object[]{userID});
	}
	
	public void setUnready(int userID){
		this.getTemplate().update("update User set state = 0 where id = ?",
				new Object[]{userID});
	}
	
	public boolean exists(User user){
		List list=this.getTemplate().queryForList("select identifier from User where identifier = ?",
				new Object[]{user.getIdentifier()});
       return list.size() == 0 ? false : true;		
	} 
	
	public void update(User user){
		System.out.println(user.toString());
		this.getTemplate().update("update User set chatterID = ? and state = ? where identifier = ?",
				new Object[]{user.getChatterID(),user.getState(),user.getIdentifier()});
	}
	
}
