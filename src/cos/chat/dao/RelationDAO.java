package cos.chat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class RelationDAO {
	
	@Autowired
	private JdbcTemplate template;
	public JdbcTemplate getTemplate() {
		return template;
	}
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public int getMatchedID(int id){
		return (int)this.getTemplate().queryForInt("SELECT womanID FROM relation WHERE manID = ?",new Object[]{id});
	}

}
